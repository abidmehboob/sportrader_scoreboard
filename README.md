# Football World Cup Scoreboard

This is a Java Spring Boot application that provides a RESTful API for managing a live scoreboard for football matches in a world cup. The application is built using reactive programming with Spring WebFlux, and stores data in an H2 database using Flyway for migration.

## Requirements

The application provides the following functionalities:

1. Start a new game, assuming initial score 0 – 0 and adding it the scoreboard. This should capture following parameters:
    - Home team
    - Away team
2. Update score. This should receive a pair of absolute scores: home team score and away team score.
3. Finish game currently in progress. This removes a match from the scoreboard.
4. Get a summary of games in progress ordered by their total score. The games with the same total score will be returned ordered by the most recently started match in the scoreboard.

## Usage

To run the application, you can use the following command:

```
mvn spring-boot:run
```
## Frontend
1. Web Portal for ongoing matches, add new match, finish match as well update match score.
image.png

### API Endpoints

#### Start a new game

```
POST /matches
```

Example request body:

```json
{
  "homeTeam": "Mexico",
  "awayTeam": "Canada"
}
```

Example response body:

```json
{
  "id": "1",
  "homeTeam": "Mexico",
  "awayTeam": "Canada",
  "homeScore": 0,
  "awayScore": 0,
  "status": "IN_PROGRESS",
  "startTime": "2023-05-06T15:00:00Z",
  "endTime": null
}
```

#### Update score

```
PATCH /matches/{id}
```

Example request body:

```json
{
  "homeScore": 1,
  "awayScore": 2
}
```

Example response body:

```json
{
  "id": "1",
  "homeTeam": "Mexico",
  "awayTeam": "Canada",
  "homeScore": 1,
  "awayScore": 2,
  "status": "IN_PROGRESS",
  "startTime": "2023-05-06T15:00:00Z",
  "endTime": null
}
```

#### Finish game

```
DELETE /matches/{id}
```

Example response body:

```json
{
  "id": "1",
  "homeTeam": "Mexico",
  "awayTeam": "Canada",
  "homeScore": 1,
  "awayScore": 2,
  "status": "FINISHED",
  "startTime": "2023-05-06T15:00:00Z",
  "endTime": "2023-05-06T15:45:00Z"
}
```

#### Get summary of games in progress

```
GET /matches/summary
```

Example response body:

```json
[
  {
    "homeTeam": "Spain",
    "awayTeam": "Brazil",
    "totalScore": 12,
    "startTime": "2023-05-06T16:00:00Z"
  },
  {
    "homeTeam": "Mexico",
    "awayTeam": "Canada",
    "totalScore": 3,
    "startTime": "2023-05-06T15:00:00Z"
  }
]
```

## Assumptions

- The application does not require any authentication or authorization.
- The application assumes that only one match can be in progress at a given time.
- The application assumes that