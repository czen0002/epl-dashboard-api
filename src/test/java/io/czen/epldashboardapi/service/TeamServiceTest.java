package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.repository.MatchRepository;
import io.czen.epldashboardapi.repository.RankingTableTeamRepository;
import io.czen.epldashboardapi.repository.TeamRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SuppressWarnings("squid:S5786")
public class TeamServiceTest {

    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String LEEDS = "Leeds";
    private final String LIVERPOOL = "Liverpool";
    private final String EVERTON = "Everton";
    private final String HOME_WON = "H";
    private final String HOME_DRAWN = "D";
    private final String SEASON22 = "2021-22";
    private final String SEASON21 = "2020-21";
    private final String SEASON20 = "2019-20";

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    private final RankingTableTeamRepository rankingTableTeamRepository;

    private MatchService matchService;
    private TeamService teamService;
    private RankingTableTeamService rankingTableTeamService;

    @Autowired
    public TeamServiceTest(TeamRepository teamRepository, MatchRepository matchRepository,
                           RankingTableTeamRepository rankingTableTeamRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
        this.rankingTableTeamRepository = rankingTableTeamRepository;
    }

    @BeforeAll
    public void setup() {
        MatchEntity matchEntity1 = new MatchEntity(ARSENAL, CHELSEA, HOME_WON);
        MatchEntity matchEntity2 = new MatchEntity(ARSENAL, LIVERPOOL, HOME_WON);
        MatchEntity matchEntity3 = new MatchEntity(ARSENAL, LEEDS, HOME_DRAWN);
        Iterable<MatchEntity> iterableMatches = Arrays.asList(matchEntity1, matchEntity2, matchEntity3);
        matchRepository.saveAll(iterableMatches);
        matchService = new MatchService(matchRepository);

        RankingTableTeamEntity tableTeamEntity1 = new RankingTableTeamEntity(ARSENAL, 4, SEASON22);
        tableTeamEntity1.setPoints(9);
        RankingTableTeamEntity tableTeamEntity2 = new RankingTableTeamEntity(CHELSEA, 5, SEASON22);
        tableTeamEntity2.setPoints(10);
        RankingTableTeamEntity tableTeamEntity3 = new RankingTableTeamEntity(LEEDS, 3, SEASON22);
        tableTeamEntity3.setPoints(3);
        RankingTableTeamEntity tableTeamEntity4 = new RankingTableTeamEntity(LIVERPOOL, 4, SEASON22);
        tableTeamEntity4.setPoints(12);
        RankingTableTeamEntity tableTeamEntity5 = new RankingTableTeamEntity(ARSENAL, 38, SEASON20);
        RankingTableTeamEntity tableTeamEntity6 = new RankingTableTeamEntity(ARSENAL, 38, SEASON21);
        Iterable<RankingTableTeamEntity> iterableTableTeams = Arrays.asList(tableTeamEntity1, tableTeamEntity2,
                tableTeamEntity3, tableTeamEntity4, tableTeamEntity5, tableTeamEntity6);
        rankingTableTeamRepository.saveAll(iterableTableTeams);
        rankingTableTeamService = new RankingTableTeamService(rankingTableTeamRepository);

        TeamEntity teamEntity1 = new TeamEntity(ARSENAL);
        TeamEntity teamEntity2 = new TeamEntity(LEEDS);
        TeamEntity teamEntity3 = new TeamEntity(LIVERPOOL);
        TeamEntity teamEntity4 = new TeamEntity(CHELSEA);
        Iterable<TeamEntity> iterableTeams = Arrays.asList(teamEntity1, teamEntity2, teamEntity3, teamEntity4);
        teamRepository.saveAll(iterableTeams);
        teamService = new TeamService(teamRepository, matchService, rankingTableTeamService);
    }

    @AfterAll
    public void cleanUp() {
        teamRepository.deleteAll();
    }

    @Test
    public void shouldGetAllTeamsOrderByTeamName() {
        List<Team> result = teamService.getAllTeamsOrderByTeamName();
        assertEquals(4, result.size());
        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
        assertEquals(LEEDS, result.get(2).getTeamName());
        assertEquals(LIVERPOOL, result.get(3).getTeamName());
    }

    @Test
    public void shouldGetTeam() {
        Team result = teamService.getTeam(ARSENAL);
        assertNotNull(result);
    }

    @Test
    public void shouldGetTeamNull() {
        Team result = teamService.getTeam(EVERTON);
        assertNull(result);
    }

    @Test
    public void shouldGetTeamWithMatches() {
        Team result = teamService.getTeamWithMatches(ARSENAL, 3);
        assertEquals(ARSENAL, result.getTeamName());
        assertEquals(3, result.getMatches().size());
    }

    @Test
    public void shouldGetTeamWithMatchesNull() {
        Team result = teamService.getTeamWithMatches(EVERTON, 3);
        assertNull(result);
    }

    @Test
    public void shouldGetTeamsBySeason() {
        List<Team> result = teamService.getTeamsBySeason(SEASON22);
        assertEquals(4, result.size());
        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(LIVERPOOL, result.get(3).getTeamName());
    }

    @Test
    public void shouldGetTeamWithAllSeasons() {
        Team team = teamService.getTeamWithSeasons(ARSENAL);
        assertEquals(3, team.getSeasons().size());
        assertEquals(SEASON22, team.getSeasons().get(0));
        assertEquals(SEASON21, team.getSeasons().get(1));
        assertEquals(SEASON20, team.getSeasons().get(2));
    }
}
