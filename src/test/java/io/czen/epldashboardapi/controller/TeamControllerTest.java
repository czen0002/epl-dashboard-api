package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.service.MatchService;
import io.czen.epldashboardapi.service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamService teamService;

    @Mock
    private MatchService matchService;

    @Test
    public void shouldGetAllTeam() {
        Team team1 = new Team("Arsenal");
        Team team2 = new Team("Chelsea");
        Iterable<Team> iterableTeams = Arrays.asList(team1, team2);
        when(teamService.getAllTeams()).thenReturn(iterableTeams);

        List<Team> result = new ArrayList<>();
        teamController.getAllTeam().forEach(result::add);

        assertEquals("Arsenal", result.get(0).getTeamName());
        assertEquals("Chelsea", result.get(1).getTeamName());
    }

    @Test
    public void shouldGetMatchesForTeamInSeason() {
        Match match1 = new Match("Arsenal", "Chelsea", "W");
        Match match2 = new Match("Arsenal", "Liverpool", "W");
        List<Match> matches = Arrays.asList(match1, match2);
        when(matchService.getMatchesBySeason(anyString(), anyString())).thenReturn(matches);

        List<Match> results = new ArrayList<>();
        teamController.getMatchesForTeamInSeason("Arsenal", "2021-22").forEach(results::add);

        assertEquals(2, results.size());
        assertEquals("Arsenal", results.get(0).getHomeTeam());
        assertEquals("Chelsea", results.get(0).getAwayTeam());
        assertEquals("W", results.get(0).getFullTimeResult());
    }
    @Test
    public void shouldGetTeam() {
        Match match1 = new Match("Arsenal", "Chelsea", "W");
        Match match2 = new Match("Arsenal", "Liverpool", "W");
        Team team1 = new Team("Arsenal");
        List<Match> matches = Arrays.asList(match1, match2);
        when(matchService.getLatestMatchesByTeam(anyString(), anyInt())).thenReturn(matches);
        when(teamService.getTeam(anyString())).thenReturn(team1);

        Team result = teamController.getTeam("Arsenal", 2);

        assertEquals(2, result.getMatches().size());
        assertEquals("Arsenal", result.getMatches().get(1).getHomeTeam());
        assertEquals("Liverpool", result.getMatches().get(1).getAwayTeam());
        assertEquals("W", result.getMatches().get(1).getFullTimeResult());
    }

    @Test void shouldGetTeamNull() {
        when(teamService.getTeam(anyString())).thenReturn(null);

        Team result = teamController.getTeam("Arsenal", 2);

        assertNull(result);
    }
}
