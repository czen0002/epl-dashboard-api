package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.entity.TeamEntity;
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
        TeamEntity teamEntity1 = new TeamEntity(ARSENAL);
        TeamEntity teamEntity2 = new TeamEntity(LEEDS);
        TeamEntity teamEntity3 = new TeamEntity(LIVERPOOL);
        TeamEntity teamEntity4 = new TeamEntity(CHELSEA);
        Iterable<TeamEntity> iterableTeams = Arrays.asList(teamEntity1, teamEntity4, teamEntity2, teamEntity3);
        when(teamRepository.findAllByOrderByTeamName()).thenReturn(iterableTeams);
        List<TeamEntity> result = new ArrayList<>();
        teamService.getAllTeamsOrderByTeamName().forEach(result::add);

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
        TeamEntity result = teamService.getTeam(anyString());

        assertEquals(ARSENAL, result.getTeamName());
    }

    @Test
    public void shouldGetTeamWithMatches() {
        TeamEntity teamEntity = new TeamEntity(ARSENAL);
        MatchEntity matchEntity1 = new MatchEntity(ARSENAL, CHELSEA, "W");
        MatchEntity matchEntity2 = new MatchEntity(ARSENAL, LIVERPOOL, "W");
        MatchEntity matchEntity3 = new MatchEntity(ARSENAL, LEEDS, "D");
        List<MatchEntity> matchEntities = Arrays.asList(matchEntity1, matchEntity2, matchEntity3);
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.of(teamEntity));
        when(matchService.getLatestMatchesByTeam(anyString(), anyInt())).thenReturn(matchEntities);
        TeamEntity result = teamService.getTeamWithMatches(ARSENAL, 3);

        assertEquals(ARSENAL, result.getTeamName());
        assertEquals(3, result.getMatchEntities().size());
    }

    @Test
    public void shouldGetNullTeam() {
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.empty());
        TeamEntity result = teamService.getTeamWithMatches(ARSENAL, 3);

        assertNull(result);
    }
}
