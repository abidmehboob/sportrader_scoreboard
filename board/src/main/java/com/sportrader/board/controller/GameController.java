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
//@Api(tags = "Game")
@RequestMapping("/scoreboard")
public class GameController {

    private final GameScoreboardFacadeImpl facade;

    public GameController(GameScoreboardFacadeImpl facade) {
        this.facade = facade;
    }

    @PostMapping("/game")
  //  @ApiOperation(value = "Start NewGame", nickname = "startNewGame", notes = "Start NewGame")
    public Mono<Game> startNewGame(@RequestBody GameRequest request) {
        Mono<Game> savedGame = Mono.just(facade.startMatch(request.getHomeTeam(), request.getAwayTeam()));
        return savedGame;
    }

    @PutMapping("/games/{gameId}/score")
 //   @ApiOperation(value = "Update Score", nickname = "updateScore", notes = "Update Score")
    public Mono<Game> updateScore(@PathVariable("gameId") String gameId, @RequestBody ScoreRequest request) {
        Mono<Game> savedGame = Mono.just(facade.updateScore(Long.valueOf(gameId), request.getHomeTeamScore(), request.getAwayTeamScore()));
        return savedGame;
    }

    @DeleteMapping("/games/{gameId}")
  //  @ApiOperation(value = "Finish Game", nickname = "finishGame", notes = "Finish Game")
    public Mono<Boolean> finishGame(@PathVariable("gameId") String gameId) {
        facade.finishMatch(Long.valueOf(gameId));
        Mono<Boolean> savedGame = Mono.just(Boolean.TRUE);
        return savedGame;
    }

    @GetMapping("/games")
   // @ApiOperation(value = "Get All Games", nickname = "games", notes = "Get All Games")
    public Flux<MatchSummary> getGamesInProgressOrderedByScore() {
        return Flux.fromIterable(facade.getMatchesOrderedByTotalScore());
    }


}


