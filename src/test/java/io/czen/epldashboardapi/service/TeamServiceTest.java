package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("squid:S5786")
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
        TeamEntity teamEntity1 = new TeamEntity(ARSENAL);
        TeamEntity teamEntity2 = new TeamEntity(LEEDS);
        TeamEntity teamEntity3 = new TeamEntity(LIVERPOOL);
        TeamEntity teamEntity4 = new TeamEntity(CHELSEA);
        Iterable<TeamEntity> iterableTeams = Arrays.asList(teamEntity1, teamEntity4, teamEntity2, teamEntity3);
        when(teamRepository.findAllByOrderByTeamName()).thenReturn(iterableTeams);
        List<Team> result = teamService.getAllTeamsOrderByTeamName();

        assertEquals(4, result.size());
        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
        assertEquals(LEEDS, result.get(2).getTeamName());
        assertEquals(LIVERPOOL, result.get(3).getTeamName());
    }

    @Test
    public void shouldGetTeam() {
        TeamEntity teamEntity = new TeamEntity(ARSENAL);
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.of(teamEntity));
        Team result = teamService.getTeam(anyString());

        assertEquals(ARSENAL, result.getTeamName());
    }

    @Test
    public void shouldGetNull() {
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.empty());
        Team result = teamService.getTeam(anyString());

        assertNull(result);
    }

    @Test
    public void shouldGetTeamWithMatches() {
        TeamEntity teamEntity = new TeamEntity(ARSENAL);
        Match match1 = new Match(ARSENAL, CHELSEA, "W");
        Match match2 = new Match(ARSENAL, LIVERPOOL, "W");
        Match match3 = new Match(ARSENAL, LEEDS, "D");
        List<Match> matches = Arrays.asList(match1, match2, match3);
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.of(teamEntity));
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
