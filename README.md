# Spring Boot Backend Practice

A hands-on backend project built to reinforce core Java backend concepts using Spring Boot and PostgreSQL. The project is structured across branches to progressively cover JDBC, JPA/Hibernate, and REST API development.

## Tech Stack

- Java 21 / Spring Boot 4
- PostgreSQL
- Docker / Docker Compose
- Maven
- Lombok
- H2 Database (for testing)

## Branches

| Branch | Description
|---|---|
| jpa-implementation|JPA/Hibernate with Spring Data JPA and REST endpoints (in progress) |
|jdbc|JDBC implementation using JdbcTemplate and DAO pattern|

## How to Run
Prerequisites: Docker, Java 21

``` bash
# Start PostgreSQL
docker-compose up -d

# Run the application
./mvnw spring-boot:run

# Or Run unit and integration tests and package
./mvnw clean verify

```

## Key Concepts Learnt

### JDBC (jdbc branch)


- DAO pattern with JdbcTemplate for clean separation of data access logic
- RowMapper for mapping SQL result sets to Java objects
- Schema initialization with schema.sql on application startup

### JPA (jpa-implementation branch) (in progress)

- Entity mapping with @Entity, @OneToMany, @ManyToOne
- Spring Data JPA repositories
- Rest Endpoints for CRUD functions
