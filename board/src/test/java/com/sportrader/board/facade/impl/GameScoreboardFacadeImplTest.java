package com.sportrader.board.facade.impl;

import com.sportrader.board.dto.Game;
import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.entity.Match;
import com.sportrader.board.service.GameScoreboardMatchService;
import org.springframework.stereotype.Service;
import service.impl.GameScoreboardServiceTest;

import java.util.ArrayList;
import java.util.List;
@Service
public class GameScoreboardFacadeImplTest {

    private final GameScoreboardServiceTest matchService;

    public GameScoreboardFacadeImplTest(GameScoreboardServiceTest matchService) {
        this.matchService = matchService;
    }

    public Game startMatch(String homeTeam, String awayTeam) {
//        Game game = convertToDto(matchService.startMatch(homeTeam, awayTeam));
        return null;
    }

    public Game updateScore(long matchId, int homeTeamScore, int awayTeamScore) {
       // Game game = convertToDto(matchService.updateMatchScore(matchId, homeTeamScore, awayTeamScore));
        return null;
    }

    public void finishMatch(long matchId) {

        //matchService.finishMatch(matchId);
    }


    public List<MatchSummary> getMatchesOrderedByTotalScore() {
        //List<MatchSummary> games = convertToDto(matchService.getMatchesOrderedByTotalScore());
        return null;
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
