package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.repository.TeamRepository;
import io.czen.epldashboardapi.service.TeamService;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeamServiceTest {

    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String LEEDS = "Leeds";
    private final String LIVERPOOL = "Liverpool";
    private final String EVERTON = "Everton";

    private final TeamRepository teamRepository;
    private TeamService teamService;

    @Autowired
    public TeamServiceTest(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @BeforeAll
    public void setup() {
        Team team1 = new Team(ARSENAL);
        Team team2 = new Team(LEEDS);
        Team team3 = new Team(LIVERPOOL);
        Team team4 = new Team(CHELSEA);
        Team[] teams = {team1, team2, team3, team4};
        Iterable<Team> iterableTeams = Arrays.asList(teams);
        teamRepository.saveAll(iterableTeams);
        teamService = new TeamService(teamRepository);
    }

    @AfterAll
    public void cleanUp() {
        teamRepository.deleteAll();
    }

    @Test
    public void shouldGetAllTeams() {
        List<Team> allTeams = new ArrayList<>();
        teamService.getAllTeams().forEach(allTeams::add);
        assertEquals(4, allTeams.size());
        assertEquals(ARSENAL, allTeams.get(0).getTeamName());
        assertEquals(CHELSEA, allTeams.get(1).getTeamName());
        assertEquals(LEEDS, allTeams.get(2).getTeamName());
        assertEquals(LIVERPOOL, allTeams.get(3).getTeamName());
    }

    @Test
    public void shouldGetTeam() {
        Team team = teamService.getTeam(ARSENAL);
        assertNotNull(team);
    }

    @Test
    public void shouldGetNull() {
        Team team = teamService.getTeam(EVERTON);
        assertNull(team);
    }
}
