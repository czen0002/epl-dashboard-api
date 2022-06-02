package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.entity.TeamEntity;
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
        TeamEntity teamEntity1 = new TeamEntity("Arsenal");
        TeamEntity teamEntity2 = new TeamEntity("Chelsea");
        Iterable<TeamEntity> iterableTeams = Arrays.asList(teamEntity1, teamEntity2);
        when(teamService.getAllTeamsOrderByTeamName()).thenReturn(iterableTeams);
        List<TeamEntity> result = new ArrayList<>();
        teamController.getAllTeamOrderByTeamName().forEach(result::add);

        assertEquals("Arsenal", result.get(0).getTeamName());
        assertEquals("Chelsea", result.get(1).getTeamName());
    }

    @Test
    public void shouldGetMatchesForTeamInSeason() {
        MatchEntity matchEntity1 = new MatchEntity("Arsenal", "Chelsea", "W");
        MatchEntity matchEntity2 = new MatchEntity("Arsenal", "Liverpool", "W");
        List<MatchEntity> matchEntities = Arrays.asList(matchEntity1, matchEntity2);
        when(matchService.getMatchesBySeason(anyString(), anyString())).thenReturn(matchEntities);
        List<MatchEntity> results = new ArrayList<>(teamController.getMatchesForTeamInSeason("Arsenal", "2021-22"));

        assertEquals(2, results.size());
        assertEquals("Arsenal", results.get(0).getHomeTeam());
        assertEquals("Chelsea", results.get(0).getAwayTeam());
        assertEquals("W", results.get(0).getFullTimeResult());
    }
    @Test
    public void shouldGetTeam() {
        MatchEntity matchEntity1 = new MatchEntity("Arsenal", "Chelsea", "W");
        MatchEntity matchEntity2 = new MatchEntity("Arsenal", "Liverpool", "W");
        TeamEntity teamEntity = new TeamEntity("Arsenal");
        List<MatchEntity> matchEntities = Arrays.asList(matchEntity1, matchEntity2);
        teamEntity.setMatchEntities(matchEntities);
        when(teamService.getTeamWithMatches(anyString(), anyInt())).thenReturn(teamEntity);
        TeamEntity result = teamController.getTeam("Arsenal", 2);

        assertEquals(2, result.getMatchEntities().size());
        assertEquals("Arsenal", result.getMatchEntities().get(1).getHomeTeam());
        assertEquals("Liverpool", result.getMatchEntities().get(1).getAwayTeam());
        assertEquals("W", result.getMatchEntities().get(1).getFullTimeResult());
    }

    @Test void shouldGetTeamNull() {
        when(teamService.getTeamWithMatches(anyString(), anyInt())).thenReturn(null);
        TeamEntity result = teamController.getTeam("Arsenal", 2);

        assertNull(result);
    }
}
