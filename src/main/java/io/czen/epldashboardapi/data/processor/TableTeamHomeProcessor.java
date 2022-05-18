package io.czen.epldashboardapi.data.processor;

import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.model.TableTeam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class TableTeamHomeProcessor implements ItemProcessor<MatchInput, TableTeam> {

    private static final Logger log = LoggerFactory.getLogger(TableTeamHomeProcessor.class);

    @Override
    public TableTeam process(MatchInput matchInput) throws Exception {
        TableTeam tableTeamHome = new TableTeam(1, 0, 0, 0, 0, 0,
                0, 0);
        tableTeamHome.setTeamName(matchInput.getHomeTeam());
        tableTeamHome.setSeason(matchInput.getSeason());
        Integer homeTeamGoals = Integer.parseInt(matchInput.getFullTimeHomeTeamGoals());
        Integer awayTeamGoals = Integer.parseInt(matchInput.getFullTimeAwayTeamGoals());
        if (homeTeamGoals > awayTeamGoals) {
            tableTeamHome.setWon(1);
            tableTeamHome.setPoints(3);
        } else if (homeTeamGoals < awayTeamGoals) {
            tableTeamHome.setLost(1);
        } else {
            tableTeamHome.setDrawn(1);
            tableTeamHome.setPoints(1);
        }
        tableTeamHome.setGoalsFor(homeTeamGoals);
        tableTeamHome.setGoalsAgainst(awayTeamGoals);
        tableTeamHome.setGoalsDifference(homeTeamGoals - awayTeamGoals);
        return tableTeamHome;
    }
}
