package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("squid:S5786")
public class MatchConverterTest {

    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String HOME_WON = "H";
    private final String HOME_DRAWN = "D";

    @Test
    public void convertMatchEntitySuccessfully() {
        MatchEntity matchEntity= new MatchEntity(ARSENAL, CHELSEA, HOME_DRAWN);
        Match result = MatchConverter.convertMatchEntity(matchEntity);

        assertEquals(ARSENAL, result.getHomeTeam());
        assertEquals(CHELSEA, result.getAwayTeam());
        assertEquals(HOME_DRAWN, result.getFullTimeResult());
    }

    @Test
    public void convertMatchEntitiesSuccessfully() {
        MatchEntity matchEntity1= new MatchEntity(ARSENAL, CHELSEA, HOME_DRAWN);
        MatchEntity matchEntity2= new MatchEntity(CHELSEA, ARSENAL, HOME_WON);
        List<MatchEntity> matchEntities = Arrays.asList(matchEntity1, matchEntity2);
        List<Match> result = MatchConverter.convertMatchEntities(matchEntities);

        assertEquals(2, result.size());
        assertEquals(ARSENAL, result.get(0).getHomeTeam());
        assertEquals(CHELSEA, result.get(1).getHomeTeam());
        assertEquals(HOME_DRAWN, result.get(0).getFullTimeResult());
        assertEquals(HOME_WON, result.get(1).getFullTimeResult());
    }

    @Test
    public void convertMatchSuccessfully() {
        Match match = new Match(ARSENAL, CHELSEA, HOME_DRAWN);
        MatchEntity result = MatchConverter.convertMatch(match);

        assertEquals(ARSENAL, result.getHomeTeam());
        assertEquals(CHELSEA, result.getAwayTeam());
        assertEquals(HOME_DRAWN, result.getFullTimeResult());
    }
}
