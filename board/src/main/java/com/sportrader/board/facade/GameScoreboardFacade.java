package com.sportrader.board.facade;

import com.sportrader.board.dto.Game;
import com.sportrader.board.dto.MatchSummary;

import java.util.List;

public interface GameScoreboardFacade {
    public Game startMatch(String homeTeam, String awayTeam);

    public Game updateScore(long matchId, int homeTeamScore, int awayTeamScore);

    public void finishMatch(long matchId);

    public List<MatchSummary> getMatchesOrderedByTotalScore();
}
