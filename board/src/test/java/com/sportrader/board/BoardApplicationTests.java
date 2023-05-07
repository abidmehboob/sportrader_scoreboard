package com.sportrader.board;

import com.sportrader.board.controller.GameController;
import com.sportrader.board.controller.GameControllerTest;
import com.sportrader.board.dto.Game;
import com.sportrader.board.dto.MatchSummary;
import com.sportrader.board.facade.GameScoreboardFacade;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import service.impl.GameScoreboardServiceTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@WebFluxTest(GameControllerTest.class)
@AutoConfigureWebTestClient
@ContextConfiguration(classes = {GameControllerTest.class, GameScoreboardServiceTest.class, Game.class})
//@Sql({"/schema.sql", "/data.sql"})
class BoardApplicationTests {

	@Mock
	private WebTestClient webClient;

	@Test
	public void testAddGame() {
		Game gameDTO = new Game();
		gameDTO.setHomeTeam("Team 1");
		gameDTO.setAwayTeam("Team 2");

		webClient.post()
				.uri("/api/games")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(gameDTO)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Game.class)
				.value(response -> {
					assertNotNull(response.getId());
					assertEquals("Team 1", response.getHomeTeam());
					assertEquals("Team 2", response.getAwayTeam());
					assertEquals(Optional.of(0), response.getHomeTeamScore());
					assertEquals(Optional.of(0), response.getAwayTeamScore());
				});
	}

	@Test
	public void testUpdateScore() {
		Game gameDTO = new Game();
		gameDTO.setId(1L);
		gameDTO.setHomeTeamScore(1);
		gameDTO.setAwayTeamScore(2);

		webClient.put()
				.uri("/api/games/{id}", gameDTO.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(gameDTO)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Game.class)
				.value(response -> {
					assertEquals(gameDTO.getId(), response.getId());
					assertEquals(gameDTO.getHomeTeam(), response.getHomeTeam());
					assertEquals(gameDTO.getAwayTeam(), response.getAwayTeam());
					assertEquals(gameDTO.getHomeTeamScore(), response.getHomeTeamScore());
					assertEquals(gameDTO.getAwayTeamScore(), response.getAwayTeamScore());
				});
	}

	@Test
	public void testGetAllGames() {
		webClient.get()
				.uri("/api/games")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(MatchSummary.class)
				.value(response -> {
					assertEquals(5, response.size());
					assertEquals("Mexico", response.get(0).getHomeTeam());
					assertEquals("Canada", response.get(0).getAwayTeam());
				});
	}


	@Test
	public void testDeleteGame() {
		webClient.delete()
				.uri("/api/games/{id}", 1L)
				.exchange()
				.expectStatus().isOk();
	}
}
