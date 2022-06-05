package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;

public final class MatchConverter {

    private MatchConverter() {
        // not called
    }

    public static Match convert(MatchEntity matchEntity) {
        Match match = new Match();
        match.setSeason(matchEntity.getSeason());
        match.setDate(matchEntity.getDate());
        match.setHomeTeam(matchEntity.getHomeTeam());
        match.setAwayTeam(matchEntity.getAwayTeam());
        match.setFullTimeHomeTeamGoals(matchEntity.getFullTimeHomeTeamGoals());
        match.setFullTimeAwayTeamGoals(matchEntity.getFullTimeAwayTeamGoals());
        match.setFullTimeResult(matchEntity.getFullTimeResult());
        match.setHalfTimeHomeTeamGoals(matchEntity.getHalfTimeHomeTeamGoals());
        match.setHalfTimeAwayTeamGoals(matchEntity.getHalfTimeAwayTeamGoals());
        match.setHalfTimeResult(matchEntity.getHalfTimeResult());
        match.setReferee(matchEntity.getReferee());
        match.setHomeTeamShots(matchEntity.getHomeTeamShots());
        match.setAwayTeamShots(matchEntity.getAwayTeamShots());
        match.setHomeTeamShotsOnTarget(matchEntity.getHomeTeamShotsOnTarget());
        match.setAwayTeamShotsOnTarget(matchEntity.getAwayTeamShotsOnTarget());
        match.setHomeTeamCorners(matchEntity.getHomeTeamCorners());
        match.setAwayTeamCorners(matchEntity.getAwayTeamCorners());
        match.setHomeTeamFoulsCommitted(matchEntity.getHomeTeamFoulsCommitted());
        match.setAwayTeamFoulsCommitted(matchEntity.getAwayTeamFoulsCommitted());
        match.setHomeTeamYellowCards(matchEntity.getHomeTeamYellowCards());
        match.setAwayTeamYellowCards(matchEntity.getAwayTeamYellowCards());
        match.setHomeTeamRedCards(matchEntity.getHomeTeamRedCards());
        match.setAwayTeamRedCards(matchEntity.getAwayTeamRedCards());
        return match;
    }
}
