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
        // Given
        Match match = new Match(ARSENAL, CHELSEA, HOME_DRAWN);
        // When
        MatchEntity result = MatchEntityMapper.mapMatch(match);
        // Then
        assertEquals(ARSENAL, result.getHomeTeam());
        assertEquals(CHELSEA, result.getAwayTeam());
        assertEquals(HOME_DRAWN, result.getFullTimeResult());
    }
}
