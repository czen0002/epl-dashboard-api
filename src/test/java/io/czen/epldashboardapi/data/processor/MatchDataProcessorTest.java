package io.czen.epldashboardapi.data.processor;


import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.model.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("squid:S5786")
public class MatchDataProcessorTest {

    private MatchDataProcessor processor;
    private MatchInput matchInput;

    @BeforeEach
    private void init() {
        processor = new MatchDataProcessor();
        matchInput = new MatchInput();
    }

    @Test
    public void processMatchInput() {
        matchInput.setId("1");
        matchInput.setSeason("2021-22");
        matchInput.setDate("2021-10-03");
        matchInput.setHomeTeam("Arsenal");
        matchInput.setAwayTeam("Chelsea");
        matchInput.setFullTimeHomeTeamGoals("0");
        matchInput.setFullTimeAwayTeamGoals("0");
        matchInput.setFullTimeResult("D");
        matchInput.setHalfTimeHomeTeamGoals("0");
        matchInput.setHalfTimeAwayTeamGoals("0");
        matchInput.setHalfTimeResult("D");
        matchInput.setReferee("S Attwell");
        matchInput.setHomeTeamShots("18");
        matchInput.setAwayTeamShots("11");
        matchInput.setHomeTeamShotsOnTarget("6");
        matchInput.setAwayTeamShotsOnTarget("4");
        matchInput.setHomeTeamCorners("3");
        matchInput.setAwayTeamCorners("4");
        matchInput.setHomeTeamFoulsCommitted("11");
        matchInput.setAwayTeamFoulsCommitted("12");
        matchInput.setHomeTeamYellowCards("1");
        matchInput.setAwayTeamYellowCards("2");
        matchInput.setHomeTeamRedCards("0");
        matchInput.setAwayTeamRedCards("0");

        Match match = processor.process(matchInput);
        assertEquals("2021-22", match.getSeason());
        assertEquals(LocalDate.of(2021, 10, 3), match.getDate());
        assertEquals("Arsenal", match.getHomeTeam());
        assertEquals("Chelsea", match.getAwayTeam());
        assertEquals("0", match.getFullTimeHomeTeamGoals());
        assertEquals("0", match.getFullTimeAwayTeamGoals());
        assertEquals("D", match.getFullTimeResult());
        assertEquals("0", match.getHalfTimeHomeTeamGoals());
        assertEquals("0", match.getHalfTimeAwayTeamGoals());
        assertEquals("D", match.getHalfTimeResult());
        assertEquals("S Attwell", match.getReferee());
        assertEquals("18", match.getHomeTeamShots());
        assertEquals("11", match.getAwayTeamShots());
        assertEquals("6", match.getHomeTeamShotsOnTarget());
        assertEquals("4", match.getAwayTeamShotsOnTarget());
        assertEquals("3", match.getHomeTeamCorners());
        assertEquals("4", match.getAwayTeamCorners());
        assertEquals("11", match.getHomeTeamFoulsCommitted());
        assertEquals("12", match.getAwayTeamFoulsCommitted());
        assertEquals("1", match.getHomeTeamYellowCards());
        assertEquals("2", match.getAwayTeamYellowCards());
        assertEquals("0", match.getHomeTeamRedCards());
        assertEquals("0", match.getAwayTeamRedCards());
    }
}
