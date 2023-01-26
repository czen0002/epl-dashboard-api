package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;

public final class MatchEntityMapper {

    private MatchEntityMapper() {
        // not called
    }

    public static MatchEntity mapMatch(Match match) {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setSeason(match.getSeason());
        matchEntity.setDate(match.getDate());
        matchEntity.setHomeTeam(match.getHomeTeam());
        matchEntity.setAwayTeam(match.getAwayTeam());
        matchEntity.setFullTimeHomeTeamGoals(match.getFullTimeHomeTeamGoals());
        matchEntity.setFullTimeAwayTeamGoals(match.getFullTimeAwayTeamGoals());
        matchEntity.setFullTimeResult(match.getFullTimeResult());
        matchEntity.setHalfTimeHomeTeamGoals(match.getHalfTimeHomeTeamGoals());
        matchEntity.setHalfTimeAwayTeamGoals(match.getHalfTimeAwayTeamGoals());
        matchEntity.setHalfTimeResult(match.getHalfTimeResult());
        matchEntity.setReferee(match.getReferee());
        matchEntity.setHomeTeamShots(match.getHomeTeamShots());
        matchEntity.setAwayTeamShots(match.getAwayTeamShots());
        matchEntity.setHomeTeamShotsOnTarget(match.getHomeTeamShotsOnTarget());
        matchEntity.setAwayTeamShotsOnTarget(match.getAwayTeamShotsOnTarget());
        matchEntity.setHomeTeamCorners(match.getHomeTeamCorners());
        matchEntity.setAwayTeamCorners(match.getAwayTeamCorners());
        matchEntity.setHomeTeamFoulsCommitted(match.getHomeTeamFoulsCommitted());
        matchEntity.setAwayTeamFoulsCommitted(match.getAwayTeamFoulsCommitted());
        matchEntity.setHomeTeamYellowCards(match.getHomeTeamYellowCards());
        matchEntity.setAwayTeamYellowCards(match.getAwayTeamYellowCards());
        matchEntity.setHomeTeamRedCards(match.getHomeTeamRedCards());
        matchEntity.setAwayTeamRedCards(match.getAwayTeamRedCards());
        return matchEntity;
    }
}
