package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
import io.czen.epldashboardapi.model.RankingTableTeam;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RankingTableTeamMapper implements Function<RankingTableTeamEntity, RankingTableTeam> {
    @Override
    public RankingTableTeam apply(RankingTableTeamEntity rankingTableTeamEntity) {
        return new RankingTableTeam(
                rankingTableTeamEntity.getTeamName(),
                rankingTableTeamEntity.getPlayed(),
                rankingTableTeamEntity.getWon(),
                rankingTableTeamEntity.getDrawn(),
                rankingTableTeamEntity.getLost(),
                rankingTableTeamEntity.getGoalsFor(),
                rankingTableTeamEntity.getGoalsAgainst(),
                rankingTableTeamEntity.getGoalsDifference(),
                rankingTableTeamEntity.getPoints(),
                rankingTableTeamEntity.getSeason()
        );
    }
}
