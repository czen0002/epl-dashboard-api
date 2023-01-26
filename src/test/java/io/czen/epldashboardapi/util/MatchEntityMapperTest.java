package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchEntityMapperTest {

    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String HOME_DRAWN = "D";

    @Test
    public void mapMatchSuccessfully() {
        Match match = new Match(ARSENAL, CHELSEA, HOME_DRAWN);
        MatchEntity result = MatchEntityMapper.mapMatch(match);

        assertEquals(ARSENAL, result.getHomeTeam());
        assertEquals(CHELSEA, result.getAwayTeam());
        assertEquals(HOME_DRAWN, result.getFullTimeResult());
    }
}
