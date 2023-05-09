package com.sportrader.board.controller;

import com.sportrader.board.dto.Game;
import com.sportrader.board.dto.GameRequest;
import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.dto.ScoreRequest;
import com.sportrader.board.facade.GameScoreboardFacade;
import com.sportrader.board.facade.impl.GameScoreboardFacadeImplTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GameScoreboardFacade facade;

    @Test
    public void testGetAllGames() {
        List<MatchSummary> games = Arrays.asList(
                new MatchSummary(1, "Team A", "Team B", 0, 0, 0, LocalDateTime.now(), LocalDateTime.now())
        );
        given(facade.getMatchesOrderedByTotalScore()).willReturn(Flux.fromIterable(games));

        webTestClient.get().uri("/scoreboard/games")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MatchSummary.class).isEqualTo(games);
    }

    @Test
    public void testCreateGame() {
        GameRequest game = new GameRequest("Team A", "Team B");
        Game gamedto = new Game(1L, "Team A", "Team B", 0, 0, LocalDateTime.now());
        given(facade.startMatch(any())).willReturn(Mono.just(gamedto));

        webTestClient.post().uri("/scoreboard/game")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(game)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Game.class).isEqualTo(gamedto);
    }

    @Test
    public void testUpdateGameScore() {
        ScoreRequest game = new ScoreRequest(1l, 1, 2);
        Game gamedto = new Game(1L, "Team A", "Team B", 1, 2, LocalDateTime.now());
        given(facade.updateScore(any())).willReturn(Mono.just(gamedto));

        webTestClient.put().uri("/scoreboard/games/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(game)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Game.class).isEqualTo(gamedto);
    }

    @Test
    public void testFinishGame() {
        given(facade.finishMatch(1l)).willReturn(Mono.empty());

        webTestClient.delete().uri("/games/1")
                .exchange()
                .expectStatus().isNoContent();
    }

}
