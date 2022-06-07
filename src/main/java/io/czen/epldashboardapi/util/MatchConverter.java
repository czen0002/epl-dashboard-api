package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;

import java.util.ArrayList;
import java.util.List;

public final class MatchConverter {

    private MatchConverter() {
        // not called
    }

    public static Match convertMatchEntity(MatchEntity matchEntity) {
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

    public static List<Match> convertMatchEntities(List<MatchEntity> matchEntities) {
        List<Match> matches = new ArrayList<>();
        matchEntities.forEach(m -> matches.add(convertMatchEntity(m)));
        return matches;
    }

    public static MatchEntity convertMatch(Match match) {
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
