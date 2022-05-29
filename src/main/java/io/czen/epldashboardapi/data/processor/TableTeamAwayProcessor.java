package io.czen.epldashboardapi.data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.model.MatchTeam;
import org.springframework.batch.item.ItemProcessor;

public class TableTeamAwayProcessor implements ItemProcessor<MatchInput, MatchTeam> {

    @Override
    public MatchTeam process(MatchInput matchInput) {
        MatchTeam matchTeamAway = new MatchTeam(0, 0, 0, 0, 0, 0, 0);
        matchTeamAway.setTeamName(matchInput.getAwayTeam());
        matchTeamAway.setSeason(matchInput.getSeason());
        Integer homeTeamGoals = Integer.parseInt(matchInput.getFullTimeHomeTeamGoals());
        Integer awayTeamGoals = Integer.parseInt(matchInput.getFullTimeAwayTeamGoals());
        if (awayTeamGoals > homeTeamGoals) {
            matchTeamAway.setWon(1);
            matchTeamAway.setPoints(3);
        } else if (awayTeamGoals < homeTeamGoals) {
            matchTeamAway.setLost(1);
        } else {
            matchTeamAway.setDrawn(1);
            matchTeamAway.setPoints(1);
        }
        matchTeamAway.setGoalsFor(awayTeamGoals);
        matchTeamAway.setGoalsAgainst(homeTeamGoals);
        matchTeamAway.setGoalsDifference(awayTeamGoals - homeTeamGoals);
        return matchTeamAway;
    }
}
