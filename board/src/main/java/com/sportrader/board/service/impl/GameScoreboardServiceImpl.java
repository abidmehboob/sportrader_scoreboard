package com.sportrader.board.service.impl;

import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.dto.ScoreRequest;
import com.sportrader.board.entity.Match;
import com.sportrader.board.repository.GameScoreboardRepository;
import com.sportrader.board.service.GameScoreboardMatchService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class GameScoreboardServiceImpl implements GameScoreboardMatchService {

    private final GameScoreboardRepository gameScoreboardRepository;

    public GameScoreboardServiceImpl(GameScoreboardRepository gameScoreboardRepository) {
        this.gameScoreboardRepository = gameScoreboardRepository;
    }

    @Override
    public Match startMatch(Match startMatch) {
        Match match = new Match();
        match.setHomeTeam(startMatch.getHomeTeam());
        match.setAwayTeam(startMatch.getAwayTeam());
        match.setHomeTeamScore(0);
        match.setAwayTeamScore(0);
        match.setStartTime(LocalDateTime.now());
        return gameScoreboardRepository.save(match);
    }

    @Override
    public Optional<Match> findById(Long matchId) {
        return gameScoreboardRepository.findById(matchId);
    }

    @Override
    public Match updateMatchScore(ScoreRequest request) {
        Match match = gameScoreboardRepository.findById(request.getMatchId())
                .orElseThrow(() -> new EntityNotFoundException("Match not found with id " + request.getMatchId()));
        match.setHomeTeamScore(request.getHomeTeamScore());
        match.setAwayTeamScore(request.getAwayTeamScore());
        match.setLastUpdateTime(LocalDateTime.now());
        return gameScoreboardRepository.save(match);
    }

    @Override
    public void finishMatch(Long matchId) {
        Match match = gameScoreboardRepository.findById(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Match not found with id " + matchId));
        gameScoreboardRepository.delete(match);
    }

    @Override
    public Flux<MatchSummary> getMatchesOrderedByTotalScore() {
        return Flux.fromIterable(convertToDto(gameScoreboardRepository.findAll(Sort.by(Sort.Order.desc("homeTeamScore"), Sort.Order.desc("awayTeamScore"), Sort.Order.desc("startTime")))));
    }

    private List<MatchSummary> convertToDto(List<Match> savedGame) {
        List<MatchSummary> games = new ArrayList<MatchSummary>();
        savedGame.stream().forEach(footballMatch -> games.add(new MatchSummary(footballMatch.getId().intValue(), footballMatch.getHomeTeam(), footballMatch.getAwayTeam(), footballMatch.getHomeTeamScore(), footballMatch.getAwayTeamScore(), footballMatch.getHomeTeamScore() + footballMatch.getAwayTeamScore(), footballMatch.getStartTime(), footballMatch.getLastUpdateTime())));
        return games;
    }
}