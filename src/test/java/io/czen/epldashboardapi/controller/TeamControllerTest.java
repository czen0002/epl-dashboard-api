package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.entity.TeamEntity;
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
    public void shouldGetAllTeamsOrderByTeamName() {
        Team team1 = new Team("Arsenal");
        Team team2 = new Team("Chelsea");
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamService.getAllTeamsOrderByTeamName()).thenReturn(teams);
        List<Team> result = teamController.getAllTeamOrderByTeamName();

        assertEquals("Arsenal", result.get(0).getTeamName());
        assertEquals("Chelsea", result.get(1).getTeamName());
    }

    @Test
    public void shouldGetMatchesForTeamInSeason() {
        Match match1 = new Match("Arsenal", "Chelsea", "W");
        Match match2 = new Match("Arsenal", "Liverpool", "W");
        List<Match> matches = Arrays.asList(match1, match2);
        when(matchService.getMatchesBySeason(anyString(), anyString())).thenReturn(matches);
        List<Match> results = teamController.getMatchesForTeamInSeason("Arsenal", "2021-22");

        assertEquals(2, results.size());
        assertEquals("Arsenal", results.get(0).getHomeTeam());
        assertEquals("Chelsea", results.get(0).getAwayTeam());
        assertEquals("W", results.get(0).getFullTimeResult());
    }

//    @Test
//    public void shouldGetTeam() {
//        MatchEntity matchEntity1 = new MatchEntity("Arsenal", "Chelsea", "W");
//        MatchEntity matchEntity2 = new MatchEntity("Arsenal", "Liverpool", "W");
//        TeamEntity teamEntity = new TeamEntity("Arsenal");
//        List<MatchEntity> matchEntities = Arrays.asList(matchEntity1, matchEntity2);
//        teamEntity.setMatchEntities(matchEntities);
//        when(teamService.getTeamWithMatches(anyString(), anyInt())).thenReturn(teamEntity);
//        TeamEntity result = teamController.getTeam("Arsenal", 2);
//
//        assertEquals(2, result.getMatchEntities().size());
//        assertEquals("Arsenal", result.getMatchEntities().get(1).getHomeTeam());
//        assertEquals("Liverpool", result.getMatchEntities().get(1).getAwayTeam());
//        assertEquals("W", result.getMatchEntities().get(1).getFullTimeResult());
//    }
//
//    @Test void shouldGetTeamNull() {
//        when(teamService.getTeamWithMatches(anyString(), anyInt())).thenReturn(null);
//        TeamEntity result = teamController.getTeam("Arsenal", 2);
//
//        assertNull(result);
//    }
}
