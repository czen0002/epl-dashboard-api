package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.RankingTableTeam;
import io.czen.epldashboardapi.service.RankingTableTeamService;
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
public class TableControllerTest {

    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String LIVERPOOL = "Liverpool";
    private final String SEASON = "2021-22";

    @InjectMocks
    private TableController tableController;

    @Mock
    private RankingTableTeamService rankingTableTeamService;

    @Test
    public void shouldGetRankingTableBySeason() {
        List<RankingTableTeam> rankingTableTeams = generateMockRankingTableTeamList();
        when(rankingTableTeamService.getRankingTableBySeason(anyString())).thenReturn(rankingTableTeams);
        List<RankingTableTeam> result = tableController.getRankingTableBySeason(SEASON);

        assertEquals(3, result.size());
        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
        assertEquals(LIVERPOOL, result.get(2).getTeamName());
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
