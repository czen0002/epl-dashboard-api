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
    private final String HOME_WON = "H";
    private final String SEASON = "2021-22";
    private final String MONTH = "3";

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
        List<Team> teams = generateMockTeamList();
        when(teamService.getAllTeamsOrderByTeamName()).thenReturn(teams);
        List<Team> result = teamController.getAllTeamOrderByTeamName();

        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
    }

    @Test
    public void shouldGetMatchesForTeamInSeason() {
        List<Match> matches = generateMockMatchList();
        when(matchService.getMatchesByTeamBySeason(anyString(), anyString())).thenReturn(matches);
        List<Match> results = teamController.getMatchesForTeamInSeason(ARSENAL, SEASON);

        assertEquals(2, results.size());
        assertEquals(ARSENAL, results.get(0).getHomeTeam());
        assertEquals(CHELSEA, results.get(0).getAwayTeam());
        assertEquals(HOME_WON, results.get(0).getFullTimeResult());
    }

    @Test
    public void shouldGetTeam() {
        List<Match> matches = generateMockMatchList();
        Team team = new Team(ARSENAL);
        team.setMatches(matches);
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
    public void shouldGetRankingTableBySeason() {
        List<RankingTableTeam> rankingTableTeams = generateMockRankingTableTeamList();
        when(rankingTableTeamService.getRankingTableBySeason(anyString())).thenReturn(rankingTableTeams);
        List<RankingTableTeam> result = teamController.getRankingTableBySeason(SEASON);
        
        assertEquals(3, result.size());
        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
        assertEquals(LIVERPOOL, result.get(2).getTeamName());
    }

    @Test
    public void shouldGetMatchesForTeamInMonthInSeason() {
        List<Match> matches = generateMockMatchList();
        when(matchService.getMatchesByTeamByMonthBySeason(anyString(), anyString(), anyString())).thenReturn(matches);
        List<Match> result = teamController.getMatchesForTeamInMonthInSeason(ARSENAL, MONTH, SEASON);

        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetMatchesInSeason() {
        List<Match> matches = generateMockMatchList();
        when(matchService.getMatchesBySeason(anyString())).thenReturn(matches);
        List<Match> result = teamController.getMatchesInSeason(SEASON);

        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetMatchesInMonthInSeason() {
        List<Match> matches = generateMockMatchList();
        when(matchService.getMatchesByMonthBySeason(anyString(), anyString())).thenReturn(matches);
        List<Match> result = teamController.getMatchesInMonthInSeason(MONTH, SEASON);

        assertEquals(2, result.size());
    }

    private List<Match> generateMockMatchList() {
        Match match1 = new Match(ARSENAL, CHELSEA, HOME_WON);
        Match match2 = new Match(ARSENAL, LIVERPOOL, HOME_WON);
        return Arrays.asList(match1, match2);
    }

    private List<Team> generateMockTeamList() {
        Team team1 = new Team(ARSENAL);
        Team team2 = new Team(CHELSEA);
        return Arrays.asList(team1, team2);
    }

    private List<RankingTableTeam> generateMockRankingTableTeamList() {
        RankingTableTeam rankingTableTeam1 = new RankingTableTeam();
        rankingTableTeam1.setTeamName(ARSENAL);
        rankingTableTeam1.setSeason(SEASON);
        RankingTableTeam rankingTableTeam2 = new RankingTableTeam();
        rankingTableTeam2.setTeamName(CHELSEA);
        rankingTableTeam2.setSeason(SEASON);
        RankingTableTeam rankingTableTeam3 = new RankingTableTeam();
        rankingTableTeam3.setTeamName(LIVERPOOL);
        rankingTableTeam3.setSeason(SEASON);
        return Arrays.asList(rankingTableTeam1, rankingTableTeam2, rankingTableTeam3);
    }
}
