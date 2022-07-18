package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.model.RankingTableTeam;
import io.czen.epldashboardapi.repository.RankingTableTeamRepository;
import io.czen.epldashboardapi.util.RankingTableTeamConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingTableTeamService {

    private final RankingTableTeamRepository rankingTableTeamRepository;

    @Autowired
    public RankingTableTeamService(RankingTableTeamRepository rankingTableTeamRepository) {
        this.rankingTableTeamRepository = rankingTableTeamRepository;
    }

    public List<RankingTableTeam> getRankingTableBySeason(String season) {
        return RankingTableTeamConverter.convertRankingTableTeamEntities(
                rankingTableTeamRepository.getBySeasonOrderByPointsDesc(season));
    }

    public List<RankingTableTeam> getTeamByTeamNameInSeasons(String teamName) {
        return RankingTableTeamConverter.convertRankingTableTeamEntities(
                rankingTableTeamRepository.getByTeamNameOrderBySeasonDesc(teamName));
    }
}
