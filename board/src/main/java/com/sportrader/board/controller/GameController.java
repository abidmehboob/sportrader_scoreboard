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
        Mono<Game> savedGame = facade.startMatch(request);
        return savedGame;
    }

    @PutMapping("/games/{gameId}/score")
 //   @ApiOperation(value = "Update Score", nickname = "updateScore", notes = "Update Score")
    public Mono<Game> updateScore(@PathVariable("gameId") String gameId, @RequestBody ScoreRequest request) {
        request.setMatchId(Long.valueOf(gameId));
        Mono<Game> savedGame = facade.updateScore(request);
        return savedGame;
    }

    @DeleteMapping("/games/{gameId}")
  //  @ApiOperation(value = "Finish Game", nickname = "finishGame", notes = "Finish Game")
    public Mono<Void> finishGame(@PathVariable("gameId") String gameId) {
        return facade.finishMatch(Long.valueOf(gameId));
    }

    @GetMapping("/games")
   // @ApiOperation(value = "Get All Games", nickname = "games", notes = "Get All Games")
    public Flux<MatchSummary> getGamesInProgressOrderedByScore() {
        return facade.getMatchesOrderedByTotalScore();
    }


}


