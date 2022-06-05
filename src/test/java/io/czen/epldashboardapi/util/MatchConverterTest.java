package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("squid:S5786")
public class MatchConverterTest {

    @Test
    public void convertMatchEntitySuccessfully() {
        MatchEntity matchEntity= new MatchEntity("Arsenal", "Chelsea", "D");
        Match result = MatchConverter.convertMatchEntity(matchEntity);

        assertEquals("Arsenal", result.getHomeTeam());
        assertEquals("Chelsea", result.getAwayTeam());
        assertEquals("D", result.getFullTimeResult());
    }

    @Test
    public void convertMatchEntitiesSuccessfully() {
        MatchEntity matchEntity1= new MatchEntity("Arsenal", "Chelsea", "D");
        MatchEntity matchEntity2= new MatchEntity("Chelsea", "Arsenal", "W");
        List<MatchEntity> matchEntities = Arrays.asList(matchEntity1, matchEntity2);
        List<Match> result = MatchConverter.convertMatchEntities(matchEntities);

        assertEquals(2, result.size());
        assertEquals("Arsenal", result.get(0).getHomeTeam());
        assertEquals("Chelsea", result.get(1).getHomeTeam());
        assertEquals("D", result.get(0).getFullTimeResult());
        assertEquals("W", result.get(1).getFullTimeResult());
    }
}
