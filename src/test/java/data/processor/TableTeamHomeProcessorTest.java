package data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.data.processor.TableTeamHomeProcessor;
import io.czen.epldashboardapi.model.TableTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableTeamHomeProcessorTest {

    private TableTeamHomeProcessor processor;
    private TableTeam tableTeamHome;
    private MatchInput matchInput;

    @BeforeEach
    private void init() {
        processor = new TableTeamHomeProcessor();
        tableTeamHome = new TableTeam();
        matchInput = new MatchInput();
    }

    @Test
    public void processMatchHomeTeamWon() throws Exception {
        matchInput.setHomeTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("3");
        matchInput.setFullTimeAwayTeamGoals("2");
        tableTeamHome = processor.process(matchInput);

        assertEquals(tableTeamHome.getTeamName(), "Arsenal");
        assertEquals(tableTeamHome.getSeason(), "2021-22");
        assertEquals(tableTeamHome.getWon(), 1);
        assertEquals(tableTeamHome.getDrawn(), 0);
        assertEquals(tableTeamHome.getLost(), 0);
        assertEquals(tableTeamHome.getPoints(), 3);
        assertEquals(tableTeamHome.getGoalsFor(), 3);
        assertEquals(tableTeamHome.getGoalsAgainst(), 2);
        assertEquals(tableTeamHome.getGoalsDifference(), 1);
        assertEquals(tableTeamHome.getPlayed(), 1);
    }

    @Test
    public void processMatchHomeTeamDrawn() throws Exception {
        matchInput.setHomeTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("0");
        matchInput.setFullTimeAwayTeamGoals("0");
        tableTeamHome = processor.process(matchInput);

        assertEquals(tableTeamHome.getTeamName(), "Arsenal");
        assertEquals(tableTeamHome.getSeason(), "2021-22");
        assertEquals(tableTeamHome.getWon(), 0);
        assertEquals(tableTeamHome.getDrawn(), 1);
        assertEquals(tableTeamHome.getLost(), 0);
        assertEquals(tableTeamHome.getPoints(), 1);
        assertEquals(tableTeamHome.getGoalsFor(), 0);
        assertEquals(tableTeamHome.getGoalsAgainst(), 0);
        assertEquals(tableTeamHome.getGoalsDifference(), 0);
        assertEquals(tableTeamHome.getPlayed(), 1);
    }

    @Test
    public void processMatchHomeTeamLost() throws Exception {
        matchInput.setHomeTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("0");
        matchInput.setFullTimeAwayTeamGoals("1");
        tableTeamHome = processor.process(matchInput);

        assertEquals(tableTeamHome.getTeamName(), "Arsenal");
        assertEquals(tableTeamHome.getSeason(), "2021-22");
        assertEquals(tableTeamHome.getWon(), 0);
        assertEquals(tableTeamHome.getDrawn(), 0);
        assertEquals(tableTeamHome.getLost(), 1);
        assertEquals(tableTeamHome.getPoints(), 0);
        assertEquals(tableTeamHome.getGoalsFor(), 0);
        assertEquals(tableTeamHome.getGoalsAgainst(), 1);
        assertEquals(tableTeamHome.getGoalsDifference(), -1);
        assertEquals(tableTeamHome.getPlayed(), 1);
    }
}
