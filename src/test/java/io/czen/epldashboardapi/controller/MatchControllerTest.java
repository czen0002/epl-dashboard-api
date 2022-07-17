package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.service.MatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("squid:S5786")
public class MatchControllerTest {

    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String LIVERPOOL = "Liverpool";
    private final String HOME_WON = "H";
    private final String SEASON = "2021-22";
    private final String MONTH = "3";

    @InjectMocks
    private MatchController matchController;

    @Mock
    private MatchService matchService;

    @Test
    public void shouldGetMatchesForTeamInMonthInSeason() {
        List<Match> matches = generateMockMatchList();
        when(matchService.getMatchesByTeamByMonthBySeason(anyString(), anyString(), anyString())).thenReturn(matches);
        List<Match> result = matchController.getMatchesForTeamInMonthInSeason(ARSENAL, MONTH, SEASON);

        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetMatchesInSeason() {
        List<Match> matches = generateMockMatchList();
        when(matchService.getMatchesBySeason(anyString())).thenReturn(matches);
        List<Match> result = matchController.getMatchesInSeason(SEASON);

        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetMatchesInMonthInSeason() {
        List<Match> matches = generateMockMatchList();
        when(matchService.getMatchesByMonthBySeason(anyString(), anyString())).thenReturn(matches);
        List<Match> result = matchController.getMatchesInMonthInSeason(MONTH, SEASON);

        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetMatchesForTeamInSeason() {
        List<Match> matches = generateMockMatchList();
        when(matchService.getMatchesByTeamBySeason(anyString(), anyString())).thenReturn(matches);
        List<Match> results = matchController.getMatchesForTeamInSeason(ARSENAL, SEASON);

        assertEquals(2, results.size());
        assertEquals(ARSENAL, results.get(0).getHomeTeam());
        assertEquals(CHELSEA, results.get(0).getAwayTeam());
        assertEquals(HOME_WON, results.get(0).getFullTimeResult());
    }

    private List<Match> generateMockMatchList() {
        Match match1 = new Match(ARSENAL, CHELSEA, HOME_WON);
        Match match2 = new Match(ARSENAL, LIVERPOOL, HOME_WON);
        return Arrays.asList(match1, match2);
    }
}
