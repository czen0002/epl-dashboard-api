package io.czen.epldashboardapi.data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.model.MatchTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchTeamHomeProcessorTest {

    private TableTeamHomeProcessor processor;
    private MatchTeam matchTeamHome;
    private MatchInput matchInput;

    @BeforeEach
    private void init() {
        processor = new TableTeamHomeProcessor();
        matchTeamHome = new MatchTeam();
        matchInput = new MatchInput();
    }

    @Test
    public void processMatchHomeTeamWon() {
        matchInput.setHomeTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("3");
        matchInput.setFullTimeAwayTeamGoals("2");
        matchTeamHome = processor.process(matchInput);

        assertEquals("Arsenal", matchTeamHome.getTeamName());
        assertEquals("2021-22", matchTeamHome.getSeason());
        assertEquals(1, matchTeamHome.getWon());
        assertEquals(0, matchTeamHome.getDrawn());
        assertEquals(0, matchTeamHome.getLost());
        assertEquals(3, matchTeamHome.getPoints());
        assertEquals(3, matchTeamHome.getGoalsFor());
        assertEquals(2, matchTeamHome.getGoalsAgainst());
        assertEquals(1, matchTeamHome.getGoalsDifference());
    }

    @Test
    public void processMatchHomeTeamDrawn() {
        matchInput.setHomeTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("0");
        matchInput.setFullTimeAwayTeamGoals("0");
        matchTeamHome = processor.process  (matchInput);

        assertEquals("Arsenal", matchTeamHome.getTeamName());
        assertEquals("2021-22", matchTeamHome.getSeason());
        assertEquals(0, matchTeamHome.getWon());
        assertEquals(1, matchTeamHome.getDrawn());
        assertEquals(0, matchTeamHome.getLost());
        assertEquals(1, matchTeamHome.getPoints());
        assertEquals(0, matchTeamHome.getGoalsFor());
        assertEquals(0, matchTeamHome.getGoalsAgainst());
        assertEquals(0, matchTeamHome.getGoalsDifference());
    }

    @Test
    public void processMatchHomeTeamLost() {
        matchInput.setHomeTeam("Arsenal");
        matchInput.setSeason("2021-22");
        matchInput.setFullTimeHomeTeamGoals("0");
        matchInput.setFullTimeAwayTeamGoals("1");
        matchTeamHome = processor.process(matchInput);

        assertEquals("Arsenal", matchTeamHome.getTeamName());
        assertEquals("2021-22", matchTeamHome.getSeason());
        assertEquals(0, matchTeamHome.getWon());
        assertEquals(0, matchTeamHome.getDrawn());
        assertEquals(1, matchTeamHome.getLost());
        assertEquals(0, matchTeamHome.getPoints());
        assertEquals(0, matchTeamHome.getGoalsFor());
        assertEquals(1, matchTeamHome.getGoalsAgainst());
        assertEquals(-1, matchTeamHome.getGoalsDifference());
    }
}
