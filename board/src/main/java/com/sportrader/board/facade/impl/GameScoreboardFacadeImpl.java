package com.sportrader.board.facade.impl;

import com.sportrader.board.dto.Game;
import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.entity.Match;
import com.sportrader.board.facade.GameScoreboardFacade;
import com.sportrader.board.service.GameScoreboardMatchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameScoreboardFacadeImpl implements GameScoreboardFacade {

    private final GameScoreboardMatchService matchService;

    public GameScoreboardFacadeImpl(GameScoreboardMatchService matchService) {
        this.matchService = matchService;
    }

    @Override
    public Game startMatch(String homeTeam, String awayTeam) {
        Game game = convertToDto(matchService.startMatch(homeTeam, awayTeam));
        return game;
    }

    @Override
    public Game updateScore(long matchId, int homeTeamScore, int awayTeamScore) {
        Game game = convertToDto(matchService.updateMatchScore(matchId, homeTeamScore, awayTeamScore));
        return game;
    }

    @Override
    public void finishMatch(long matchId) {
        matchService.finishMatch(matchId);
    }

    @Override
    public List<MatchSummary> getMatchesOrderedByTotalScore() {
        List<MatchSummary> games = convertToDto(matchService.getMatchesOrderedByTotalScore());
        return games;
    }

    private Game convertToDto(Match game) {
        return new Game(game.getId(), game.getHomeTeam(), game.getAwayTeam(), game.getHomeTeamScore(), game.getAwayTeamScore(), game.getStartTime());
    }

    private List<MatchSummary> convertToDto(List<Match> savedGame) {
        List<MatchSummary> games = new ArrayList<MatchSummary>();
        savedGame.stream().forEach(footballMatch -> games.add(new MatchSummary(footballMatch.getId().intValue(), footballMatch.getHomeTeam(), footballMatch.getAwayTeam(), footballMatch.getHomeTeamScore(), footballMatch.getAwayTeamScore(), footballMatch.getHomeTeamScore() + footballMatch.getAwayTeamScore(), footballMatch.getStartTime(), footballMatch.getLastUpdateTime())));
        return games;
    }

}
