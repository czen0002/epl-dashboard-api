package io.czen.epldashboardapi.data.processor;


import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.entity.MatchEntity;
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

        MatchEntity matchEntity = processor.process(matchInput);
        assertEquals(1, matchEntity.getId());
        assertEquals("2021-22", matchEntity.getSeason());
        assertEquals(LocalDate.of(2021, 10, 3), matchEntity.getDate());
        assertEquals("Arsenal", matchEntity.getHomeTeam());
        assertEquals("Chelsea", matchEntity.getAwayTeam());
        assertEquals("0", matchEntity.getFullTimeHomeTeamGoals());
        assertEquals("0", matchEntity.getFullTimeAwayTeamGoals());
        assertEquals("D", matchEntity.getFullTimeResult());
        assertEquals("0", matchEntity.getHalfTimeHomeTeamGoals());
        assertEquals("0", matchEntity.getHalfTimeAwayTeamGoals());
        assertEquals("D", matchEntity.getHalfTimeResult());
        assertEquals("S Attwell", matchEntity.getReferee());
        assertEquals("18", matchEntity.getHomeTeamShots());
        assertEquals("11", matchEntity.getAwayTeamShots());
        assertEquals("6", matchEntity.getHomeTeamShotsOnTarget());
        assertEquals("4", matchEntity.getAwayTeamShotsOnTarget());
        assertEquals("3", matchEntity.getHomeTeamCorners());
        assertEquals("4", matchEntity.getAwayTeamCorners());
        assertEquals("11", matchEntity.getHomeTeamFoulsCommitted());
        assertEquals("12", matchEntity.getAwayTeamFoulsCommitted());
        assertEquals("1", matchEntity.getHomeTeamYellowCards());
        assertEquals("2", matchEntity.getAwayTeamYellowCards());
        assertEquals("0", matchEntity.getHomeTeamRedCards());
        assertEquals("0", matchEntity.getAwayTeamRedCards());
    }
}
