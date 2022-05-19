package io.czen.epldashboardapi.data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.model.MatchTeam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class TableTeamAwayProcessor implements ItemProcessor<MatchInput, MatchTeam> {

    private static final Logger log = LoggerFactory.getLogger(TableTeamAwayProcessor.class);

    @Override
    public MatchTeam process(MatchInput matchInput) throws Exception {
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
