package io.czen.epldashboardapi.data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;


public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(MatchInput matchInput) throws Exception {
        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setSeason(matchInput.getSeason());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setHomeTeam(matchInput.getHomeTeam());
        match.setAwayTeam(matchInput.getAwayTeam());
        match.setFullTimeHomeTeamGoals(matchInput.getFullTimeHomeTeamGoals());
        match.setFullTimeAwayTeamGoals(matchInput.getFullTimeAwayTeamGoals());
        match.setFullTimeResult(matchInput.getFullTimeResult());
        match.setHalfTimeHomeTeamGoals(matchInput.getHalfTimeHomeTeamGoals());
        match.setHalfTimeAwayTeamGoals(matchInput.getHalfTimeAwayTeamGoals());
        match.setHalfTimeResult(matchInput.getHalfTimeResult());
        match.setReferee(matchInput.getReferee());
        match.setHomeTeamShots(matchInput.getHomeTeamShots());
        match.setAwayTeamShots(matchInput.getAwayTeamShots());
        match.setHomeTeamShotsOnTarget(matchInput.getHomeTeamShotsOnTarget());
        match.setAwayTeamShotsOnTarget(matchInput.getAwayTeamShotsOnTarget());
        match.setHomeTeamCorners(matchInput.getHomeTeamCorners());
        match.setAwayTeamCorners(matchInput.getAwayTeamCorners());
        match.setHomeTeamFoulsCommitted(matchInput.getHomeTeamFoulsCommitted());
        match.setAwayTeamFoulsCommitted(matchInput.getAwayTeamFoulsCommitted());
        match.setHomeTeamYellowCards(matchInput.getHomeTeamYellowCards());
        match.setAwayTeamYellowCards(matchInput.getAwayTeamYellowCards());
        match.setHomeTeamRedCards(matchInput.getHomeTeamRedCards());
        match.setAwayTeamRedCards(matchInput.getAwayTeamRedCards());

        return match;
    }
}
