package io.czen.epldashboardapi.data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.model.TableTeam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class TableTeamAwayProcessor implements ItemProcessor<MatchInput, TableTeam> {

    private static final Logger log = LoggerFactory.getLogger(TableTeamAwayProcessor.class);

    @Override
    public TableTeam process(MatchInput matchInput) throws Exception {
        TableTeam tableTeamAway = new TableTeam(1, 0, 0, 0, 0, 0,
                0, 0);
        tableTeamAway.setTeamName(matchInput.getAwayTeam());
        tableTeamAway.setSeason(matchInput.getSeason());
        Integer homeTeamGoals = Integer.parseInt(matchInput.getFullTimeHomeTeamGoals());
        Integer awayTeamGoals = Integer.parseInt(matchInput.getFullTimeAwayTeamGoals());
        if (awayTeamGoals > homeTeamGoals) {
            tableTeamAway.setWon(1);
            tableTeamAway.setPoints(3);
        } else if (awayTeamGoals < homeTeamGoals) {
            tableTeamAway.setLost(1);
        } else {
            tableTeamAway.setDrawn(1);
            tableTeamAway.setPoints(1);
        }
        tableTeamAway.setGoalsFor(awayTeamGoals);
        tableTeamAway.setGoalsAgainst(homeTeamGoals);
        tableTeamAway.setGoalsDifference(awayTeamGoals - homeTeamGoals);
        return tableTeamAway;
    }
}
