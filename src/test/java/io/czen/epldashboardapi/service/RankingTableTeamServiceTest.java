package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
import io.czen.epldashboardapi.model.RankingTableTeam;
import io.czen.epldashboardapi.repository.RankingTableTeamRepository;
import io.czen.epldashboardapi.util.RankingTableTeamMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SuppressWarnings("squid:S5786")
public class RankingTableTeamServiceTest {

    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String LEEDS = "Leeds";
    private final String LIVERPOOL = "Liverpool";
    private final String SEASON22 = "2021-22";
    private final String SEASON21 = "2020-21";
    private final String SEASON20 = "2019-20";

    private final RankingTableTeamRepository rankingTableTeamRepository;
    private final RankingTableTeamMapper rankingTableTeamMapper = new RankingTableTeamMapper();
    private RankingTableTeamService rankingTableTeamService;

    @Autowired
    public RankingTableTeamServiceTest(RankingTableTeamRepository rankingTableTeamRepository) {
        this.rankingTableTeamRepository = rankingTableTeamRepository;
    }

    @BeforeAll
    public void setup() {
        RankingTableTeamEntity teamEntity1 = new RankingTableTeamEntity(ARSENAL, 4, SEASON22);
        teamEntity1.setPoints(9);
        RankingTableTeamEntity teamEntity2 = new RankingTableTeamEntity(CHELSEA, 5, SEASON22);
        teamEntity2.setPoints(10);
        RankingTableTeamEntity teamEntity3 = new RankingTableTeamEntity(LEEDS, 3, SEASON22);
        teamEntity3.setPoints(3);
        RankingTableTeamEntity teamEntity4 = new RankingTableTeamEntity(LIVERPOOL, 4, SEASON22);
        teamEntity4.setPoints(12);
        RankingTableTeamEntity teamEntity5 = new RankingTableTeamEntity(ARSENAL, 38, SEASON20);
        RankingTableTeamEntity teamEntity6 = new RankingTableTeamEntity(ARSENAL, 38, SEASON21);
        Iterable<RankingTableTeamEntity> iterableTeams = Arrays.asList(teamEntity1, teamEntity2, teamEntity3,
                teamEntity4, teamEntity5, teamEntity6);
        rankingTableTeamRepository.saveAll(iterableTeams);
        rankingTableTeamService = new RankingTableTeamService(rankingTableTeamRepository, rankingTableTeamMapper);
    }

    @AfterAll
    public void cleanUp() {
        rankingTableTeamRepository.deleteAll();
    }

    @Test
    public void getRankingTableTeamsBySeason() {
        List<RankingTableTeam> result = rankingTableTeamService.getRankingTableBySeason(SEASON22);
        assertEquals(4, result.size());
        assertEquals(LIVERPOOL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
        assertEquals(ARSENAL, result.get(2).getTeamName());
        assertEquals(LEEDS, result.get(3).getTeamName());
    }

    @Test
    public void getTeamInSeason() {
        List<RankingTableTeam> result = rankingTableTeamService.getTeamByTeamNameInSeasons(ARSENAL);
        assertEquals(3, result.size());
        assertEquals(SEASON22, result.get(0).getSeason());
        assertEquals(SEASON21, result.get(1).getSeason());
        assertEquals(SEASON20, result.get(2).getSeason());
    }
}
