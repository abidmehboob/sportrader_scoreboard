package com.sportrader.board.facade;

import com.sportrader.board.dto.Game;
import com.sportrader.board.dto.GameRequest;
import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.dto.ScoreRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GameScoreboardFacade {
    public Mono<Game> startMatch(GameRequest request);

    public Mono<Game> updateScore(ScoreRequest request);

    public Mono<Void> finishMatch(long matchId);

    public Flux<MatchSummary> getMatchesOrderedByTotalScore();
}
