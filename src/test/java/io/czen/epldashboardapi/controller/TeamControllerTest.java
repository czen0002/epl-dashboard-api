package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("squid:S5786")
public class TeamControllerTest {
    
    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String LIVERPOOL = "Liverpool";
    private final String HOME_WON = "H";
    private final String SEASON22 = "2021-22";
    private final String SEASON21 = "2020-21";

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamService teamService;

    @Test
    public void shouldGetAllTeamsOrderByTeamName() {
        List<Team> teams = generateMockTeamList();
        when(teamService.getAllTeamsOrderByTeamName()).thenReturn(teams);
        List<Team> result = teamController.getAllTeamOrderByTeamName();

        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
    }

    @Test
    public void shouldGetTeam() {
        Team team = generateMockTeam();
        when(teamService.getTeamWithMatches(anyString(), anyInt())).thenReturn(team);
        Team result = teamController.getTeam(ARSENAL, 2);

        assertEquals(2, result.getMatches().size());
        assertEquals(ARSENAL, result.getMatches().get(1).getHomeTeam());
        assertEquals(LIVERPOOL, result.getMatches().get(1).getAwayTeam());
        assertEquals(HOME_WON, result.getMatches().get(1).getFullTimeResult());
    }

    @Test
    public void shouldGetTeamNull() {
        when(teamService.getTeamWithMatches(anyString(), anyInt())).thenReturn(null);
        Team result = teamController.getTeam(ARSENAL, 2);

        assertNull(result);
    }

    @Test
    public void shouldGetTeamsBySeason() {
        List<Team> teams = generateMockTeamList();
        when(teamService.getTeamsBySeason(anyString())).thenReturn(teams);
        List<Team> result = teamController.getTeamsBySeason(SEASON22);
        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetTeamWithAllSeasons() {
        Team team = generateMockTeam();
        when(teamService.getTeamWithSeasons(anyString())).thenReturn(team);
        Team result = teamController.getTeamWithSeasons(ARSENAL);
        assertEquals(2, result.getSeasons().size());
    }

    private List<Team> generateMockTeamList() {
        Team team1 = new Team(ARSENAL);
        Team team2 = new Team(CHELSEA);
        return Arrays.asList(team1, team2);
    }

    private Team generateMockTeam() {
        Match match1 = new Match(ARSENAL, CHELSEA, HOME_WON);
        Match match2 = new Match(ARSENAL, LIVERPOOL, HOME_WON);
        List<Match> matches = Arrays.asList(match1, match2);
        List<String> seasons = Arrays.asList(SEASON22, SEASON21);
        Team team = new Team(ARSENAL);
        team.setMatches(matches);
        team.setSeasons(seasons);
        return team;
    }

}
