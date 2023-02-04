package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.entity.MatchEntity;

import java.util.ArrayList;
import java.util.List;

public final class MatchMapper {

    private MatchMapper() {
        // not called
    }

    public static Match mapMatchEntity(MatchEntity matchEntity) {
        return new Match(
                matchEntity.getSeason(),
                matchEntity.getDate(),
                matchEntity.getHomeTeam(),
                matchEntity.getAwayTeam(),
                matchEntity.getFullTimeHomeTeamGoals(),
                matchEntity.getFullTimeAwayTeamGoals(),
                matchEntity.getFullTimeResult(),
                matchEntity.getHalfTimeHomeTeamGoals(),
                matchEntity.getHalfTimeAwayTeamGoals(),
                matchEntity.getHalfTimeResult(),
                matchEntity.getReferee(),
                matchEntity.getHomeTeamShots(),
                matchEntity.getAwayTeamShots(),
                matchEntity.getHomeTeamShotsOnTarget(),
                matchEntity.getAwayTeamShotsOnTarget(),
                matchEntity.getHomeTeamCorners(),
                matchEntity.getAwayTeamCorners(),
                matchEntity.getHomeTeamFoulsCommitted(),
                matchEntity.getAwayTeamFoulsCommitted(),
                matchEntity.getHomeTeamYellowCards(),
                matchEntity.getAwayTeamYellowCards(),
                matchEntity.getHomeTeamRedCards(),
                matchEntity.getAwayTeamRedCards()
        );
    }

    public static List<Match> mapMatchEntities(List<MatchEntity> matchEntities) {
        List<Match> matches = new ArrayList<>();
        matchEntities.forEach(m -> matches.add(mapMatchEntity(m)));
        return matches;
    }
}
