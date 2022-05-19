package io.czen.epldashboardapi.data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.model.MatchTeam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class TableTeamHomeProcessor implements ItemProcessor<MatchInput, MatchTeam> {

    private static final Logger log = LoggerFactory.getLogger(TableTeamHomeProcessor.class);

    @Override
    public MatchTeam process(MatchInput matchInput) throws Exception {
        MatchTeam matchTeamHome = new MatchTeam(0, 0, 0, 0, 0, 0, 0);
        matchTeamHome.setTeamName(matchInput.getHomeTeam());
        matchTeamHome.setSeason(matchInput.getSeason());
        Integer homeTeamGoals = Integer.parseInt(matchInput.getFullTimeHomeTeamGoals());
        Integer awayTeamGoals = Integer.parseInt(matchInput.getFullTimeAwayTeamGoals());
        if (homeTeamGoals > awayTeamGoals) {
            matchTeamHome.setWon(1);
            matchTeamHome.setPoints(3);
        } else if (homeTeamGoals < awayTeamGoals) {
            matchTeamHome.setLost(1);
        } else {
            matchTeamHome.setDrawn(1);
            matchTeamHome.setPoints(1);
        }
        matchTeamHome.setGoalsFor(homeTeamGoals);
        matchTeamHome.setGoalsAgainst(awayTeamGoals);
        matchTeamHome.setGoalsDifference(homeTeamGoals - awayTeamGoals);
        return matchTeamHome;
    }
}
