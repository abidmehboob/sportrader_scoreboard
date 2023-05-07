package com.sportrader.board.controller;

import com.sportrader.board.dto.Game;
import com.sportrader.board.dto.GameRequest;
import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.dto.ScoreRequest;
import com.sportrader.board.facade.impl.GameScoreboardFacadeImpl;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/scoreboard")
public class GameController {

    private final GameScoreboardFacadeImpl facade;

    public GameController(GameScoreboardFacadeImpl facade) {
        this.facade = facade;
    }

    @PostMapping("/game")
    public Mono<Game> startNewGame(@RequestBody GameRequest request) {
        Mono<Game> savedGame = Mono.just(facade.startMatch(request.getHomeTeam(), request.getAwayTeam()));
        return savedGame;
    }

    @PutMapping("/games/{gameId}/score")
    public Mono<Game> updateScore(@PathVariable("gameId") String gameId, @RequestBody ScoreRequest request) {
        Mono<Game> savedGame = Mono.just(facade.updateScore(Long.valueOf(gameId), request.getHomeTeamScore(), request.getAwayTeamScore()));
        return savedGame;
    }

    @DeleteMapping("/games/{gameId}")
    public Mono<Boolean> finishGame(@PathVariable("gameId") String gameId) {
        facade.finishMatch(Long.valueOf(gameId));
        Mono<Boolean> savedGame = Mono.just(Boolean.TRUE);
        return savedGame;
    }

    @GetMapping("/games")
    public Flux<MatchSummary> getGamesInProgressOrderedByScore() {
        return Flux.fromIterable(facade.getMatchesOrderedByTotalScore());
    }


}


