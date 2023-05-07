package com.sportrader.board.service;

import com.sportrader.board.entity.Match;

import java.util.List;
import java.util.Optional;

public interface GameScoreboardMatchService {
    Match startMatch(String homeTeam, String awayTeam);

    Optional<Match> findById(Long matchId);

    Match updateMatchScore(Long matchId, int homeTeamScore, int awayTeamScore);

    void finishMatch(Long matchId);

    List<Match> getMatchesOrderedByTotalScore();
}
