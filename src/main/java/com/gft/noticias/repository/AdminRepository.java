package com.gft.noticias.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.gft.noticias.entity.Admin;

public interface AdminRepository extends Neo4jRepository<Admin,Long> {
    
}
