package com.gft.noticias.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import com.gft.noticias.entity.Etiqueta;

@DataNeo4jTest
class EtiquetaRepositoryTest {

	private static Neo4j embeddedDatabaseServer;

	@BeforeAll
	static void initializeNeo4j() {

		embeddedDatabaseServer = Neo4jBuilders.newInProcessBuilder() 
			.withDisabledServer() 
			.build();
	}

	@DynamicPropertySource 
	static void neo4jProperties(DynamicPropertyRegistry registry) {

		registry.add("spring.neo4j.uri", embeddedDatabaseServer::boltURI);
		registry.add("spring.neo4j.authentication.username", () -> "neo4j");
		registry.add("spring.neo4j.authentication.password", () -> null);
	}

	@AfterAll
	static void stopNeo4j() {

		embeddedDatabaseServer.close(); 
	}

	@Test
	public void findSomethingShouldWork(@Autowired Neo4jClient client) {

		Optional<Long> result = client.query("MATCH (n) RETURN COUNT(n)")
			.fetchAs(Long.class)
			.one();
		assertFalse(result.isEmpty());
	}

	@Test 
	public void obterEtiqueta(@Autowired Neo4jClient client){
		System.out.println(client.query("MATCH (n) RETURN (n)").toString());
		//assertTrue(result.get().getNome() == "Teste");
	}
}
	
