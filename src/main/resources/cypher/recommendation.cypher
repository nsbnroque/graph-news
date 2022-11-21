 MATCH (u:Usuario)-[:TEM_INTERESSE_EM]->(:Etiqueta)<-[:TEM_INTERESSE_EM]-(outro:Usuario)-[:TEM_INTERESSE_EM]->(e:Etiqueta)
    WHERE
       NOT((u)-[:TEM_INTERESSE_EM]->(e))
    RETURN e