package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.model.RankingTableTeam;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.service.MatchService;
import io.czen.epldashboardapi.service.RankingTableTeamService;
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
    private final String HOME_WON = "W";
    private final String SEASON = "2021-22";

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamService teamService;

    @Mock
    private MatchService matchService;

    @Mock
    private RankingTableTeamService rankingTableTeamService;

    @Test
    public void shouldGetAllTeamsOrderByTeamName() {
        Team team1 = new Team(ARSENAL);
        Team team2 = new Team(CHELSEA);
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamService.getAllTeamsOrderByTeamName()).thenReturn(teams);
        List<Team> result = teamController.getAllTeamOrderByTeamName();

        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
    }

    @Test
    public void shouldGetMatchesForTeamInSeason() {
        Match match1 = new Match(ARSENAL, CHELSEA, HOME_WON);
        Match match2 = new Match(ARSENAL, LIVERPOOL, HOME_WON);
        List<Match> matches = Arrays.asList(match1, match2);
        when(matchService.getMatchesBySeason(anyString(), anyString())).thenReturn(matches);
        List<Match> results = teamController.getMatchesForTeamInSeason(ARSENAL, SEASON);

        assertEquals(2, results.size());
        assertEquals(ARSENAL, results.get(0).getHomeTeam());
        assertEquals(CHELSEA, results.get(0).getAwayTeam());
        assertEquals(HOME_WON, results.get(0).getFullTimeResult());
    }

    @Test
    public void shouldGetTeam() {
        Match match1 = new Match(ARSENAL, CHELSEA, HOME_WON);
        Match match2 = new Match(ARSENAL, LIVERPOOL, HOME_WON);
        Team team = new Team(ARSENAL);
        List<Match> matches = Arrays.asList(match1, match2);
        team.setMatches(matches);
        when(teamService.getTeamWithMatches(anyString(), anyInt())).thenReturn(team);
        Team result = teamController.getTeam(ARSENAL, 2);

        assertEquals(2, result.getMatches().size());
        assertEquals(ARSENAL, result.getMatches().get(1).getHomeTeam());
        assertEquals(LIVERPOOL, result.getMatches().get(1).getAwayTeam());
        assertEquals("W", result.getMatches().get(1).getFullTimeResult());
    }

    @Test
    public void shouldGetTeamNull() {
        when(teamService.getTeamWithMatches(anyString(), anyInt())).thenReturn(null);
        Team result = teamController.getTeam(ARSENAL, 2);

        assertNull(result);
    }

    @Test
    public void shouldGetRankingTableBySeason() {
        RankingTableTeam rankingTableTeam1 = new RankingTableTeam();
        rankingTableTeam1.setTeamName(ARSENAL);
        rankingTableTeam1.setSeason(SEASON);
        RankingTableTeam rankingTableTeam2 = new RankingTableTeam();
        rankingTableTeam2.setTeamName(CHELSEA);
        rankingTableTeam2.setSeason(SEASON);
        RankingTableTeam rankingTableTeam3 = new RankingTableTeam();
        rankingTableTeam3.setTeamName(LIVERPOOL);
        rankingTableTeam3.setSeason(SEASON);
        List<RankingTableTeam> rankingTableTeams = Arrays.asList(rankingTableTeam1, rankingTableTeam2, rankingTableTeam3);
        when(rankingTableTeamService.getRankingTableBySeason(anyString())).thenReturn(rankingTableTeams);
        List<RankingTableTeam> result = teamController.getRankingTableBySeason(SEASON);
        
        assertEquals(3, result.size());
        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
        assertEquals(LIVERPOOL, result.get(2).getTeamName());
    }
}
