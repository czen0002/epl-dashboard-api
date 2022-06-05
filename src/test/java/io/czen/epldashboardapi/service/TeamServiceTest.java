package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.repository.MatchRepository;
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

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    private MatchService matchService;
    private TeamService teamService;

    @Autowired
    public TeamServiceTest(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @BeforeAll
    public void setup() {
        TeamEntity teamEntity1 = new TeamEntity(ARSENAL);
        TeamEntity teamEntity2 = new TeamEntity(LEEDS);
        TeamEntity teamEntity3 = new TeamEntity(LIVERPOOL);
        TeamEntity teamEntity4 = new TeamEntity(CHELSEA);
        Iterable<TeamEntity> iterableTeams = Arrays.asList(teamEntity1, teamEntity2, teamEntity3, teamEntity4);
        teamRepository.saveAll(iterableTeams);

        MatchEntity matchEntity1 = new MatchEntity(ARSENAL, CHELSEA, "W");
        MatchEntity matchEntity2 = new MatchEntity(ARSENAL, LIVERPOOL, "W");
        MatchEntity matchEntity3 = new MatchEntity(ARSENAL, LEEDS, "D");
        Iterable<MatchEntity> iterableMatches = Arrays.asList(matchEntity1, matchEntity2, matchEntity3);
        matchRepository.saveAll(iterableMatches);

        matchService = new MatchService(matchRepository);
        teamService = new TeamService(teamRepository, matchService);
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
}
