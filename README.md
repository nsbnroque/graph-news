# Desafio Api - Notícias



## Resumo

API Rest de notícias desenvolvida em Spring e Neo4j, usando o serviço em nuvem AuraDB.


### Introdução ao Spring Data Neo4j 

Um banco de dados em grafos é especializado em armazenar e recuperar redes de informação. É principalmente eficaz em armazenar dados como nós e relacionamentos entre outros nós ou entre um mesmo nó. Propriedades podem ser adicionadas tanto aos nós quanto aos relacionamentos, permitindo uma melhor performance de recuperação e consulta nessas estruturas. Os nós podem ser etiquetados com uma ou mais etiquetas, relacionamentos sempre terão uma direção e um nome.

Spring Data Neo4j ou SDN é a nova geração de módulo de Spring Data criado pela Neo4j, Inc. desenvolvido em colaboração com o time de Spring Data da VMWare, empresa de software especializada em virtualização e computação em nuvem. O SDN depende completamente do Neo4j Data Driver, às vezes chamado de Bolt, que é usado como um protocolo similar ao JDBC com os bancos de dados relacionais.

SDN é uma biblioteca de Mapeamento-Grafo-Objeto (OGM). Uma OGM mapeia os nós e seus relacionamentos aos objetos e referências ao modelo do domínio. Intâncias de objetos são mapeadas como nós enquanto as referências aos objetos serão mapeadas como relacionamentos. Ao mesmo tempo, provê flexibilidade ao desenvolvedor para desenvolver suas próprias querys customizadas, quando aquelas proporcionadas pelo SDN não forem suficientes.

#### Documentação: https://docs.spring.io/spring-data/neo4j/docs/current/reference/html/


## Versões

Java 17

Spring-boot-starter-neo4j 3.0.0

spring-boot-starter-web 3.0.0

spring-boot-starter-webflux 3.0.0

springdoc-openapi-ui 1.6.4

spring-boot-starter-mail 3.0.0

spring-boot-starter-security 3.0.0

## Spring Security 

Para que o Spring Security conseguisse funcionar com o Neo4j foi necessário implementar e reescrever o Authentication Provider, no projeto representado pela classe Neo4jAuthenticationProvider.

A implementação realizada no projeto é uma versão atualizada da implementação sugerida por este repositório: https://github.com/ikwattro/spring-security-neo4j

Atualizada para a nova configuração do Spring Security com o apoio deste artigo: https://www.baeldung.com/spring-security-authentication-provider

Todos os usuarios e admins possuem a mesma senha



## Endpoints

** /admin (só podem ser acessados por ROLE_ADMIN) **

** /usuarios (só podem ser acessados por ROLE_USER e mesmo id do usuario autenticado) **

## Detalhes
O email usado para enviar os emails gerados pela aplicação foi bloqueado pelo Outlook, não sei se ele terá sido liberado até a data que o presente projeto foi corrigido. Não aconselho o uso de qualquer email pessoal para tal. Escrevi um endpoint que envia um só email para um usuário.

O parâmetro data é opcional na aplicação, caso uma data não seja passada a data do dia atual será enviada para a aplicação.

## Status do projeto

Funcionalidades do projeto: 

Cadastro de administrador e usuario são realizados no mesmo endpoint, sendo obrigatório passar o role da pessoa: ROLE_ADMIN ou ROLE_USER . Usuário e Admin são dois tipos de nós (entidades) diferentes.

Cadastro de etiqueta para usuário, é possível colocar nomes com espaço e acentos, isso não irá causar problemas na busca por notícias. (A alteração é relizada antes da busca pela notícia.)

Histórico de parâmetros acessados 

Histórico de etiquetas mais acessadas 

Acesso às notícias com as etiquetas cadastradas 

Popular banco 

Testes unitários 

OpenAPI 
 
Endpoint para envio de e-mail com notícias da data corrente para usuários de acordo com suas etiquetas (somente adm)

