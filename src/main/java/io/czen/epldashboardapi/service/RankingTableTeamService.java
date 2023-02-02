package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.model.RankingTableTeam;
import io.czen.epldashboardapi.repository.RankingTableTeamRepository;
import io.czen.epldashboardapi.util.RankingTableTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingTableTeamService {

    private final RankingTableTeamRepository rankingTableTeamRepository;
    private final RankingTableTeamMapper rankingTableTeamMapper;

    @Autowired
    public RankingTableTeamService(
            RankingTableTeamRepository rankingTableTeamRepository,
            RankingTableTeamMapper rankingTableTeamMapper) {
        this.rankingTableTeamRepository = rankingTableTeamRepository;
        this.rankingTableTeamMapper = rankingTableTeamMapper;
    }

    public List<RankingTableTeam> getRankingTableBySeason(String season) {
        return rankingTableTeamRepository.getBySeasonOrderByPointsDesc(season)
                .stream()
                .map(rankingTableTeamMapper)
                .collect(Collectors.toList());
    }

    public List<RankingTableTeam> getTeamByTeamNameInSeasons(String teamName) {
        return rankingTableTeamRepository.getByTeamNameOrderBySeasonDesc(teamName)
                .stream()
                .map(rankingTableTeamMapper)
                .collect(Collectors.toList());
    }
}
