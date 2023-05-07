package com.sportrader.board.controller;

import com.sportrader.board.dto.Game;
import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.facade.impl.GameScoreboardFacadeImplTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GameControllerTest {

    @Autowired
    private WebTestClient webClient;


//    private final GameScoreboardFacadeImplTest facade;
//
//    public GameControllerTest(GameScoreboardFacadeImplTest facade) {
//        this.facade = facade;
//    }


    @Test
    public void testGetAllGames() {
        MatchSummary game1 = new MatchSummary("Mexico", "Canada");
        MatchSummary game2 = new MatchSummary("Spain", "Brazil");
        List<MatchSummary> games = Arrays.asList(game1, game2);

//        when(facade.getMatchesOrderedByTotalScore()).thenReturn((List<MatchSummary>) Flux.fromIterable(games));
//
//        webClient.get()
//                .uri("/games")
//                .exchange()
//                .expectStatus().isOk()
//                .expectBodyList(MatchSummary.class)
//                .isEqualTo(games);
    }

    @Test
    public void testCreateGame() {
        Game game = new Game("Mexico", "Canada");

//        when(facade.startMatch(game.getHomeTeam(),game.getAwayTeam())).thenReturn(game);

//        webClient.post()
//                .uri("/games")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(game))
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(Game.class)
//                .isEqualTo(game);
    }
}

