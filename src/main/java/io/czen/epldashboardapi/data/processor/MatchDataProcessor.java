package io.czen.epldashboardapi.data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.entity.MatchEntity;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;


public class MatchDataProcessor implements ItemProcessor<MatchInput, MatchEntity> {

    @Override
    public MatchEntity process(MatchInput matchInput) {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(Long.parseLong(matchInput.getId()));
        matchEntity.setSeason(matchInput.getSeason());
        matchEntity.setDate(LocalDate.parse(matchInput.getDate()));
        matchEntity.setHomeTeam(matchInput.getHomeTeam());
        matchEntity.setAwayTeam(matchInput.getAwayTeam());
        matchEntity.setFullTimeHomeTeamGoals(matchInput.getFullTimeHomeTeamGoals());
        matchEntity.setFullTimeAwayTeamGoals(matchInput.getFullTimeAwayTeamGoals());
        matchEntity.setFullTimeResult(matchInput.getFullTimeResult());
        matchEntity.setHalfTimeHomeTeamGoals(matchInput.getHalfTimeHomeTeamGoals());
        matchEntity.setHalfTimeAwayTeamGoals(matchInput.getHalfTimeAwayTeamGoals());
        matchEntity.setHalfTimeResult(matchInput.getHalfTimeResult());
        matchEntity.setReferee(matchInput.getReferee());
        matchEntity.setHomeTeamShots(matchInput.getHomeTeamShots());
        matchEntity.setAwayTeamShots(matchInput.getAwayTeamShots());
        matchEntity.setHomeTeamShotsOnTarget(matchInput.getHomeTeamShotsOnTarget());
        matchEntity.setAwayTeamShotsOnTarget(matchInput.getAwayTeamShotsOnTarget());
        matchEntity.setHomeTeamCorners(matchInput.getHomeTeamCorners());
        matchEntity.setAwayTeamCorners(matchInput.getAwayTeamCorners());
        matchEntity.setHomeTeamFoulsCommitted(matchInput.getHomeTeamFoulsCommitted());
        matchEntity.setAwayTeamFoulsCommitted(matchInput.getAwayTeamFoulsCommitted());
        matchEntity.setHomeTeamYellowCards(matchInput.getHomeTeamYellowCards());
        matchEntity.setAwayTeamYellowCards(matchInput.getAwayTeamYellowCards());
        matchEntity.setHomeTeamRedCards(matchInput.getHomeTeamRedCards());
        matchEntity.setAwayTeamRedCards(matchInput.getAwayTeamRedCards());

        return matchEntity;
    }
}
