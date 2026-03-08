# GameVault API

A REST API for managing a personal game library, built with Spring Boot.

## Tech Stack

- **Java 17**
- **Spring Boot 3** – web framework
- **Spring Data JPA** – database access
- **H2** – in-memory database (ready to swap for PostgreSQL)
- **Bean Validation** – input validation
- **JUnit 5 + Mockito** – unit testing

## Getting Started

**Prerequisites:** Java 17+, Maven

```bash
git clone https://github.com/your-username/gamevault.git
cd gamevault
mvn spring-boot:run
```

The API will be available at `http://localhost:8080/api/games`

The H2 database console is available at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:gamevaultdb`
- Username: `sa` / Password: *(empty)*

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/games` | Get all games |
| GET | `/api/games/{id}` | Get a game by ID |
| GET | `/api/games/search?title=zelda` | Search games by title |
| GET | `/api/games/filter?genre=RPG` | Filter by genre |
| GET | `/api/games/filter?platform=PC` | Filter by platform |
| POST | `/api/games` | Add a new game |
| PUT | `/api/games/{id}` | Update a game |
| DELETE | `/api/games/{id}` | Delete a game |

## Example Request

```bash
curl -X POST http://localhost:8080/api/games \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Elden Ring",
    "genre": "RPG",
    "platform": "PC",
    "releaseYear": 2022,
    "rating": 9.5
  }'
```

## Running Tests

```bash
mvn test
```

## Project Structure

```
src/
├── main/java/com/example/gamevault/
│   ├── controller/     # REST endpoints
│   ├── service/        # Business logic
│   ├── repository/     # Database access
│   ├── model/          # Database entity
│   ├── dto/            # API data transfer object
│   └── exception/      # Error handling
└── test/
    └── service/        # Unit tests
```
