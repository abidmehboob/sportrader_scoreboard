package com.sportrader.board.facade.impl;

import com.sportrader.board.dto.Game;
import com.sportrader.board.dto.GameRequest;
import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.dto.ScoreRequest;
import com.sportrader.board.entity.Match;
import com.sportrader.board.facade.GameScoreboardFacade;
import com.sportrader.board.service.GameScoreboardMatchService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameScoreboardFacadeImpl implements GameScoreboardFacade {

    private final GameScoreboardMatchService matchService;

    public GameScoreboardFacadeImpl(GameScoreboardMatchService matchService) {
        this.matchService = matchService;
    }

    @Override
    public Mono<Game> startMatch(GameRequest request) {
        Match startMatch = new Match();
        startMatch.setHomeTeam(request.getHomeTeam());
        startMatch.setAwayTeam(request.getAwayTeam());
        Game game = convertToDto(matchService.startMatch(startMatch));
        return Mono.just(game);
    }

    @Override
    public Mono<Game> updateScore(ScoreRequest request) {
        Game game = convertToDto(matchService.updateMatchScore(request));
        return Mono.just(game);
    }

    @Override
    public Mono<Void> finishMatch(long matchId) {
        matchService.finishMatch(matchId);
        return Mono.empty();
    }

    @Override
    public Flux<MatchSummary> getMatchesOrderedByTotalScore() {
        Flux<MatchSummary> games = matchService.getMatchesOrderedByTotalScore();
        return games;
    }

    private Game convertToDto(Match game) {
        return new Game(game.getId(), game.getHomeTeam(), game.getAwayTeam(), game.getHomeTeamScore(), game.getAwayTeamScore(), game.getStartTime());
    }



}
