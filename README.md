# Afterword — Personal Book Tracking API

Afterword is a personal book tracking RESTful API for managing a catalogue 
of books and authors. Built with Spring Boot 4 and PostgreSQL, with a 
focus on production-style practices — API versioning, environment variable 
externalisation, Flyway schema migrations, MockMVC integration tests, and 
containerised deployment via Docker and Docker Compose.

## Tech Stack

- Java 21 / Spring Boot 4
- PostgreSQL
- Docker / Docker Compose
- Flyway (database migrations)
- Maven
- Lombok
- H2 Database (for testing)

## Getting Started

### Prerequisites

- Docker and Docker Compose

### Setup

1. Clone the repository
2. Copy the example env file and fill in your values:
```bash
cp .env.example .env
```
3. Start the application:
```bash
docker compose up -d
```

The API will be available at `http://localhost:8080`.

### Running Locally (without Docker)

Prerequisites: Java 21, a running PostgreSQL instance (ensure a PostgreSQL database matching the name in DB_URL exists beforehand)

Export the required environment variables:
```bash
export DB_URL=jdbc:postgresql://localhost:5432/afterword
export POSTGRES_USER=youruser
export POSTGRES_PASSWORD=yourpassword
```

Then run:
```bash
./mvnw spring-boot:run
```

### Running Tests

```bash
./mvnw clean verify
```

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/authors` | Create an author |
| GET | `/api/v1/authors` | Get all authors (pagination enabled) |
| GET | `/api/v1/authors/{id}` | Get author by ID |
| PUT | `/api/v1/authors/{id}` | Full update an author |
| PATCH | `/api/v1/authors/{id}` | Partial update an author |
| DELETE | `/api/v1/authors/{id}` | Delete an author |
| PUT | `/api/v1/books/{isbn}` | Upsert a book (create or full update) |
| GET | `/api/v1/books` | Get all books (pagination enabled) |
| GET | `/api/v1/books/{isbn}` | Get book by ISBN |
| PATCH | `/api/v1/books/{isbn}` | Partial update a book |
| DELETE | `/api/v1/books/{isbn}` | Delete a book |
| GET | `/healthcheck` | Healthcheck endpoint (returns HTTP Status 200) |

A Postman collection is available in the `/postman` directory for manual testing.

## Engineering Decisions

**API versioning** — All endpoints are prefixed with `/api/v1/` to allow non-breaking API evolution.

**Flyway migrations** — Schema changes are managed via versioned Flyway migration scripts rather than Hibernate auto-DDL, enabling reproducible and auditable schema evolution across environments.

**MockMVC integration tests** — Integration tests are written with MockMVC and run against an in-memory H2 database, providing automated regression coverage without requiring a live PostgreSQL instance.

**Multi-stage Docker build** — The Dockerfile uses a multi-stage build to keep the final image lean, separating the Maven build stage from the runtime stage.

**Environment variable externalisation** — All sensitive config (DB credentials, connection URL) is externalised via `.env` and never hardcoded.

## Planned

- User accounts and shelf management (Want to Read / Currently Reading / Read)
- User authentication via JWT
- Open Library integration for book enrichment and deduplication
- Ratings, notes, and reading history
- Telegram bot frontend
- Redis caching and Resilience4j circuit breaker for external API calls
- Prometheus and Grafana observability stack
