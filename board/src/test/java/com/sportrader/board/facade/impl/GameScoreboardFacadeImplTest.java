package com.sportrader.board.facade.impl;

import com.sportrader.board.dto.Game;
import com.sportrader.board.dto.GameRequest;
import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.dto.ScoreRequest;
import com.sportrader.board.entity.Match;
import com.sportrader.board.service.GameScoreboardMatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameScoreboardFacadeImplTest {

    @Mock
    private GameScoreboardMatchService gameScoreboardMatchService;


    @InjectMocks
    private GameScoreboardFacadeImpl gameScoreboardFacade;

    @Test
    public void testGetAllGames() {
        // Given
        List<MatchSummary> gameDTOList = new ArrayList<>();
        gameDTOList.add(new MatchSummary(1, "Team A", "Team B", 0, 0, 0, LocalDateTime.now(), LocalDateTime.now()));
        gameDTOList.add(new MatchSummary(2, "Team C", "Team D", 0, 0, 0, LocalDateTime.now(), LocalDateTime.now()));
        Mockito.when(gameScoreboardMatchService.getMatchesOrderedByTotalScore()).thenReturn(Flux.fromIterable(gameDTOList));

        // When
        Flux<MatchSummary> result = gameScoreboardFacade.getMatchesOrderedByTotalScore();

        // Then
        StepVerifier.create(result)
                .expectNext(gameDTOList.get(0))
                .expectNext(gameDTOList.get(1))
                .expectComplete()
                .verify();

        verify(gameScoreboardMatchService, Mockito.times(1)).getMatchesOrderedByTotalScore();
    }

    @Test
    public void testCreateGame() {
        // Given
        Match gameEntity = new Match("Team A", "Team B");
        Mockito.when(gameScoreboardMatchService.startMatch(gameEntity)).thenReturn(gameEntity);
        GameRequest request = new GameRequest("Team A", "Team B");
        Game game = new Game(1l, "Team A", "Team B", 0, 0, LocalDateTime.now());
        // When
        Mono<Game> result = gameScoreboardFacade.startMatch(request);

        // Then
        StepVerifier.create(result)
                .expectNextMatches(dto -> gameEntity != null
                        && game.getHomeTeam().equals(gameEntity.getHomeTeam())
                        && game.getAwayTeam().equals(gameEntity.getAwayTeam())
                        && game.getHomeTeamScore() == gameEntity.getHomeTeamScore()
                        && game.getAwayTeamScore() == gameEntity.getAwayTeamScore())
                .expectComplete()
                .verify();

        verify(gameScoreboardMatchService, Mockito.times(1)).startMatch(gameEntity);
    }

    @Test
    public void testUpdateGame() {
        // Given
        Match gameEntity = new Match(1l, "Team A", "Team B", 0, 0, LocalDateTime.now());
        Game game = new Game(1l, "Team A", "Team B", 0, 0, LocalDateTime.now());
        ScoreRequest scoreRequest = new ScoreRequest(1l, 1, 2);
        Mockito.when(gameScoreboardMatchService.findById(game.getId())).thenReturn(Optional.of(gameEntity));
        Mockito.when(gameScoreboardMatchService.updateMatchScore(scoreRequest)).thenReturn(gameEntity);

        // When
        Mono<Game> result = gameScoreboardFacade.updateScore(scoreRequest);

        StepVerifier.create(result)
                .expectNextMatches(dto -> gameEntity != null
                        && game.getHomeTeam().equals(gameEntity.getHomeTeam())
                        && game.getAwayTeam().equals(gameEntity.getAwayTeam())
                        && game.getHomeTeamScore() == gameEntity.getHomeTeamScore()
                        && game.getAwayTeamScore() == gameEntity.getAwayTeamScore())
                .expectComplete()
                .verify();

        verify(gameScoreboardMatchService, Mockito.times(1)).startMatch(gameEntity);

    }

    @Test
    public void finishGame() {
        // create a mock game object
        Game game = new Game(1l, "Team A", "Team B", 0, 0, LocalDateTime.now());
        game.setId(1L);

        // mock the repository's deleteById() method to return void
        doNothing().when(gameScoreboardMatchService).finishMatch(game.getId());

        // call the removeGame() method
        gameScoreboardFacade.finishMatch(game.getId());

        // verify that the deleteById() method is called once with the correct game id
        verify(gameScoreboardMatchService, times(1)).finishMatch(game.getId());
    }
}

