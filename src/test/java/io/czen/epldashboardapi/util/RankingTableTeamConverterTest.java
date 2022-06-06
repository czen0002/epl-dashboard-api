package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
import io.czen.epldashboardapi.model.RankingTableTeam;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("squid:S5786")
public class RankingTableTeamConverterTest {
    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String SEASON = "2021-22";
    @Test
    public void convertRankingTableTeamEntitySuccessfully() {
        RankingTableTeamEntity rankingTableTeamEntity = new RankingTableTeamEntity(ARSENAL, 3, 1, 1,
                1, 3, 1, 2, 4, SEASON);
        RankingTableTeam result = RankingTableTeamConverter.convertRankingTableTeamEntity(rankingTableTeamEntity);
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
    @Test
    public void convertRankingTableTeamEntitiesSuccessfully() {
        RankingTableTeamEntity rankingTableTeamEntity1 = new RankingTableTeamEntity();
        RankingTableTeamEntity rankingTableTeamEntity2 = new RankingTableTeamEntity();
        rankingTableTeamEntity1.setTeamName(ARSENAL);
        rankingTableTeamEntity2.setTeamName(CHELSEA);
        List<RankingTableTeamEntity> rankingTableTeamEntities = Arrays.asList(rankingTableTeamEntity1, rankingTableTeamEntity2);
        List<RankingTableTeam> result = RankingTableTeamConverter.convertRankingTableTeamEntities(rankingTableTeamEntities);
        assertEquals(2, result.size());
        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
    }
}
