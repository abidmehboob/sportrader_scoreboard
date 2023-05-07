package service.impl;

import com.sportrader.board.entity.Match;
import com.sportrader.board.repository.GameScoreboardRepository;
import com.sportrader.board.service.GameScoreboardMatchService;
import com.sportrader.board.service.impl.GameScoreboardServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameScoreboardServiceTest {

    @Mock
    private GameScoreboardRepository matchRepository;

    @InjectMocks
    private GameScoreboardMatchService service = new GameScoreboardServiceImpl(matchRepository);

    @Test
    public void testGetAllGames() {
        Match match1 = new Match("Mexico", "Canada");
        Match match2 = new Match("Spain", "Brazil");
        List<Match> matches = Arrays.asList(match1, match2);

        when(matchRepository.findAll()).thenReturn(matches);

        List<Match> expectedGames = Arrays.asList(
                new Match("Mexico", "Canada"),
                new Match("Spain", "Brazil")
        );

//        StepVerifier.create(service.getMatchesOrderedByTotalScore())
//                .expectNextMatches(g -> g.equals(expectedGames.get(0)))
//                .expectNextMatches(g -> g.equals(expectedGames.get(1)))
//                .verifyComplete();
    }

    @Test
    public void testCreateGame() {
        Match game = new Match("Mexico", "Canada");
        Match match = new Match("Mexico", "Canada");

        when(matchRepository.save(match)).thenReturn(match);

//        StepVerifier.create(service.startMatch(game.getHomeTeam(),game.getAwayTeam()))
//                .expectNextMatches(g -> g.equals(game))
//                .verifyComplete();
    }
}
