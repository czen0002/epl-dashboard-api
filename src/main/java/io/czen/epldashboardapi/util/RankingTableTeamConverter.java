package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
import io.czen.epldashboardapi.model.RankingTableTeam;

import java.util.ArrayList;
import java.util.List;

public final class RankingTableTeamConverter {
    private RankingTableTeamConverter() {
        // not called
    }
    public static RankingTableTeam convertRankingTableTeamEntity(RankingTableTeamEntity rankingTableTeamEntity) {
        RankingTableTeam rankingTableTeam = new RankingTableTeam();
        rankingTableTeam.setTeamName(rankingTableTeamEntity.getTeamName());
        rankingTableTeam.setPlayed(rankingTableTeamEntity.getPlayed());
        rankingTableTeam.setWon(rankingTableTeamEntity.getWon());
        rankingTableTeam.setDrawn(rankingTableTeamEntity.getDrawn());
        rankingTableTeam.setLost(rankingTableTeamEntity.getLost());
        rankingTableTeam.setGoalsFor(rankingTableTeamEntity.getGoalsFor());
        rankingTableTeam.setGoalsAgainst(rankingTableTeamEntity.getGoalsAgainst());
        rankingTableTeam.setGoalsDifference(rankingTableTeamEntity.getGoalsDifference());
        rankingTableTeam.setPoints(rankingTableTeamEntity.getPoints());
        rankingTableTeam.setSeason(rankingTableTeamEntity.getSeason());
        return rankingTableTeam;
    }
    public static List<RankingTableTeam> convertRankingTableTeamEntities(Iterable<RankingTableTeamEntity> rankingTableTeamEntities) {
        List<RankingTableTeam> rankingTableTeams = new ArrayList<>();
        rankingTableTeamEntities.forEach(t -> rankingTableTeams.add(convertRankingTableTeamEntity(t)));
        return rankingTableTeams;
    }
}
