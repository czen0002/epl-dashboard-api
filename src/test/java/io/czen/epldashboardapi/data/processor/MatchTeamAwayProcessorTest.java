package data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.data.processor.TableTeamAwayProcessor;
import io.czen.epldashboardapi.model.MatchTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchTeamAwayProcessorTest {

    private TableTeamAwayProcessor processor;
    private MatchTeam matchTeamAway;
    private MatchInput matchInput;

    @BeforeEach
    private void init() {
        processor = new TableTeamAwayProcessor();
        matchTeamAway = new MatchTeam();
        matchInput = new MatchInput();
    }

    @Test
    public void processMatchAwayTeamWon() throws Exception {
        matchInput.setAwayTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("0");
        matchInput.setFullTimeAwayTeamGoals("2");
        matchTeamAway = processor.process(matchInput);

        assertEquals("Arsenal", matchTeamAway.getTeamName());
        assertEquals("2021-22", matchTeamAway.getSeason());
        assertEquals(1, matchTeamAway.getWon());
        assertEquals(0, matchTeamAway.getDrawn());
        assertEquals(0, matchTeamAway.getLost());
        assertEquals(3, matchTeamAway.getPoints());
        assertEquals(2, matchTeamAway.getGoalsFor());
        assertEquals(0, matchTeamAway.getGoalsAgainst());
        assertEquals(2, matchTeamAway.getGoalsDifference());
    }

    @Test
    public void processMatchAwayTeamDrawn() throws Exception {
        matchInput.setAwayTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("2");
        matchInput.setFullTimeAwayTeamGoals("2");
        matchTeamAway = processor.process(matchInput);

        assertEquals("Arsenal", matchTeamAway.getTeamName());
        assertEquals("2021-22", matchTeamAway.getSeason());
        assertEquals(0, matchTeamAway.getWon());
        assertEquals(1, matchTeamAway.getDrawn());
        assertEquals(0, matchTeamAway.getLost());
        assertEquals(1, matchTeamAway.getPoints());
        assertEquals(2, matchTeamAway.getGoalsFor());
        assertEquals(2, matchTeamAway.getGoalsAgainst());
        assertEquals(0, matchTeamAway.getGoalsDifference());
    }

    @Test
    public void processMatchAwayTeamLost() throws Exception {
        matchInput.setAwayTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("3");
        matchInput.setFullTimeAwayTeamGoals("1");
        matchTeamAway = processor.process(matchInput);

        assertEquals("Arsenal", matchTeamAway.getTeamName());
        assertEquals("2021-22", matchTeamAway.getSeason());
        assertEquals(0, matchTeamAway.getWon());
        assertEquals(0, matchTeamAway.getDrawn());
        assertEquals(1, matchTeamAway.getLost());
        assertEquals(0, matchTeamAway.getPoints());
        assertEquals(1, matchTeamAway.getGoalsFor());
        assertEquals(3, matchTeamAway.getGoalsAgainst());
        assertEquals(-2, matchTeamAway.getGoalsDifference());
    }
}
