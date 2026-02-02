# Royaume (CPE) - Spring Boot

Small Spring Boot service that fetches quests from the royaume API, stores them in Postgres, and schedules their expiry. It supports two modes:

- IHM mode: the flow is triggered by an HTTP call.
- AUTO mode: the flow is triggered automatically on a schedule.

## Prerequisites

- Java 25 (see `pom.xml`)
- Docker (for Postgres)

## Configuration

Main settings live in `src/main/resources/application.properties`:

- `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`
- `royaume.api.url` (remote API base URL)
- `royaume.ihm.group` (group name)
- `royaume.mode` (`ihm` or `auto`)
- `royaume.auto.period-ms` (auto scheduler delay in ms)

## Run the database (optional)

```bash
docker compose up -d
```

To stop and remove volumes:

```bash
docker compose down -v
```

## Run the app

```bash
./mvnw spring-boot:run
```

To run in AUTO mode from the command line (overrides properties):

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--royaume.mode=auto --royaume.auto.period-ms=30000"
```

## Run tests

```bash
./mvnw test
```

## HTTP endpoint (IHM mode)

- `GET /api/royaume/quests`  
  Triggers the quest flow and returns stored quests.

## Project structure (high level)

- `controller/` HTTP entrypoints (IHM mode)
- `scheduler/` AUTO entrypoints
- `service/` business logic
- `client/` remote API client
- `domain/` JPA entities
- `repository/` persistence
- `config/` Spring configuration
