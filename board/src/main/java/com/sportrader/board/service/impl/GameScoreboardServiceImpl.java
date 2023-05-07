package com.sportrader.board.service.impl;

import com.sportrader.board.entity.Match;
import com.sportrader.board.repository.GameScoreboardRepository;
import com.sportrader.board.service.GameScoreboardMatchService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Match startMatch(String homeTeam, String awayTeam) {
        Match match = new Match();
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
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
    public Match updateMatchScore(Long matchId, int homeTeamScore, int awayTeamScore) {
        Match match = gameScoreboardRepository.findById(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Match not found with id " + matchId));
        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);
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
    public List<Match> getMatchesOrderedByTotalScore() {
        return gameScoreboardRepository.findAll(Sort.by(Sort.Order.desc("homeTeamScore"), Sort.Order.desc("awayTeamScore"), Sort.Order.desc("startTime")));
    }
}