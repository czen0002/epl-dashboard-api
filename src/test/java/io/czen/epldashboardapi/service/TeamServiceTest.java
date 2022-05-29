package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.repository.TeamRepository;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeamServiceTest {

    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String LEEDS = "Leeds";
    private final String LIVERPOOL = "Liverpool";

    @InjectMocks
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private MatchService matchService;

    @Test
    public void shouldGetAllTeamsOrderByTeamName() {
        Team team1 = new Team(ARSENAL);
        Team team2 = new Team(LEEDS);
        Team team3 = new Team(LIVERPOOL);
        Team team4 = new Team(CHELSEA);
        Iterable<Team> iterableTeams = Arrays.asList(team1, team4, team2, team3);
        when(teamRepository.findAllByOrderByTeamName()).thenReturn(iterableTeams);
        List<Team> result = new ArrayList<>();
        teamService.getAllTeamsOrderByTeamName().forEach(result::add);

        assertEquals(4, result.size());
        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
        assertEquals(LEEDS, result.get(2).getTeamName());
        assertEquals(LIVERPOOL, result.get(3).getTeamName());
    }

    @Test
    public void shouldGetTeam() {
        Team team = new Team(ARSENAL);
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.of(team));
        Team result = teamService.getTeam(anyString());

        assertEquals(ARSENAL, result.getTeamName());
    }

    @Test
    public void shouldGetTeamWithMatches() {
        Team team = new Team(ARSENAL);
        Match match1 = new Match(ARSENAL, CHELSEA, "W");
        Match match2 = new Match(ARSENAL, LIVERPOOL, "W");
        Match match3 = new Match(ARSENAL, LEEDS, "D");
        List<Match> matches = Arrays.asList(match1, match2, match3);
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.of(team));
        when(matchService.getLatestMatchesByTeam(anyString(), anyInt())).thenReturn(matches);
        Team result = teamService.getTeamWithMatches(ARSENAL, 3);

        assertEquals(ARSENAL, result.getTeamName());
        assertEquals(3, result.getMatches().size());
    }

    @Test
    public void shouldGetNullTeam() {
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.empty());
        Team result = teamService.getTeamWithMatches(ARSENAL, 3);

        assertNull(result);
    }
}
