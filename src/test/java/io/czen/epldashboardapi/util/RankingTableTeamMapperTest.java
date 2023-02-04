package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
import io.czen.epldashboardapi.model.RankingTableTeam;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankingTableTeamMapperTest {

    private final String ARSENAL = "Arsenal";
    private final String SEASON = "2021-22";

    private final RankingTableTeamMapper rankingTableTeamMapper = new RankingTableTeamMapper();

    @Test
    public void mapRankingTableTeamEntitySuccessfully() {
        // Given
        Optional<RankingTableTeamEntity> optionalRankingTableTeamEntity = Optional.of(
                new RankingTableTeamEntity(
                        ARSENAL,
                        3,
                        1,
                        1,
                        1,
                        3,
                        1,
                        2,
                        4,
                        SEASON)
        );
        // When
        RankingTableTeam result = optionalRankingTableTeamEntity.map(rankingTableTeamMapper).orElse(null);
        // Then
        assertEquals(ARSENAL, result.getTeamName());
        assertEquals(3, result.getPlayed());
        assertEquals(1, result.getWon());
        assertEquals(1, result.getDrawn());
        assertEquals(1, result.getLost());
        assertEquals(3, result.getGoalsFor());
        assertEquals(1, result.getGoalsAgainst());
        assertEquals(2, result.getGoalsDifference());
        assertEquals(4, result.getPoints());
        assertEquals(SEASON, result.getSeason());
    }
}
