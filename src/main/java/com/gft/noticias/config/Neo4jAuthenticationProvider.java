package com.gft.noticias.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Neo4jAuthenticationProvider implements AuthenticationProvider {
    private final Driver driver;
    private final PasswordEncoder passwordEncoder;

    public Neo4jAuthenticationProvider(Driver driver, PasswordEncoder passwordEncoder) {
        this.driver = driver;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        String encodedPassword = passwordEncoder.encode(password);
        try (Session session = driver.session()) {
            List<Record> results = session.run("MATCH (n) WHERE n.email = $name RETURN n",
            Map.of("name", name, "password", encodedPassword)).list();

            if (results.isEmpty()) {
                return null;
            }

            Node user = results.get(0).get("n").asNode();
            String role = user.get("role").asString();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new SimpleGrantedAuthority(role));
            
            final UserDetails principal = new User(name, password, authorities);
            return new UsernamePasswordAuthenticationToken(principal, password, authorities);
        }
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
