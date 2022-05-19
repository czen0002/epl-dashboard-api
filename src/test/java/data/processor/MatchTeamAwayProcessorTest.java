package data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.data.processor.TableTeamAwayProcessor;
import io.czen.epldashboardapi.model.TableTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableTeamAwayProcessorTest {

    private TableTeamAwayProcessor processor;
    private TableTeam tableTeamAway;
    private MatchInput matchInput;

    @BeforeEach
    private void init() {
        processor = new TableTeamAwayProcessor();
        tableTeamAway = new TableTeam();
        matchInput = new MatchInput();
    }

    @Test
    public void processMatchAwayTeamWon() throws Exception {
        matchInput.setAwayTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("0");
        matchInput.setFullTimeAwayTeamGoals("2");
        tableTeamAway = processor.process(matchInput);

        assertEquals(tableTeamAway.getTeamName(), "Arsenal");
        assertEquals(tableTeamAway.getSeason(), "2021-22");
        assertEquals(tableTeamAway.getWon(), 1);
        assertEquals(tableTeamAway.getDrawn(), 0);
        assertEquals(tableTeamAway.getLost(), 0);
        assertEquals(tableTeamAway.getPoints(), 3);
        assertEquals(tableTeamAway.getGoalsFor(), 2);
        assertEquals(tableTeamAway.getGoalsAgainst(), 0);
        assertEquals(tableTeamAway.getGoalsDifference(), 2);
        assertEquals(tableTeamAway.getPlayed(), 1);
    }

    @Test
    public void processMatchAwayTeamDrawn() throws Exception {
        matchInput.setAwayTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("2");
        matchInput.setFullTimeAwayTeamGoals("2");
        tableTeamAway = processor.process(matchInput);

        assertEquals(tableTeamAway.getTeamName(), "Arsenal");
        assertEquals(tableTeamAway.getSeason(), "2021-22");
        assertEquals(tableTeamAway.getWon(), 0);
        assertEquals(tableTeamAway.getDrawn(), 1);
        assertEquals(tableTeamAway.getLost(), 0);
        assertEquals(tableTeamAway.getPoints(), 1);
        assertEquals(tableTeamAway.getGoalsFor(), 2);
        assertEquals(tableTeamAway.getGoalsAgainst(), 2);
        assertEquals(tableTeamAway.getGoalsDifference(), 0);
        assertEquals(tableTeamAway.getPlayed(), 1);
    }

    @Test
    public void processMatchAwayTeamLost() throws Exception {
        matchInput.setAwayTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("3");
        matchInput.setFullTimeAwayTeamGoals("1");
        tableTeamAway = processor.process(matchInput);

        assertEquals(tableTeamAway.getTeamName(), "Arsenal");
        assertEquals(tableTeamAway.getSeason(), "2021-22");
        assertEquals(tableTeamAway.getWon(), 0);
        assertEquals(tableTeamAway.getDrawn(), 0);
        assertEquals(tableTeamAway.getLost(), 1);
        assertEquals(tableTeamAway.getPoints(), 0);
        assertEquals(tableTeamAway.getGoalsFor(), 1);
        assertEquals(tableTeamAway.getGoalsAgainst(), 3);
        assertEquals(tableTeamAway.getGoalsDifference(), -2);
        assertEquals(tableTeamAway.getPlayed(), 1);
    }
}
