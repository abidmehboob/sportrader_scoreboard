package com.sportrader.board.service;

import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.dto.ScoreRequest;
import com.sportrader.board.entity.Match;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

public interface GameScoreboardMatchService {
    Match startMatch(Match startMatch);

    Optional<Match> findById(Long matchId);

    Match updateMatchScore(ScoreRequest request);

    void finishMatch(Long matchId);

    Flux<MatchSummary> getMatchesOrderedByTotalScore();
}
