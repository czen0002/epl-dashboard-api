package io.czen.epldashboardapi.data.writer;

import io.czen.epldashboardapi.model.MatchTeam;
import io.czen.epldashboardapi.model.TableTeam;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MatchTeamWriter implements ItemWriter<MatchTeam> {

    private final EntityManager em;

    @Autowired
    public MatchTeamWriter(EntityManager em) {
        this.em = em;
    }

    @Override
    public void write(List<? extends MatchTeam> matchTeams) throws Exception {
        for (MatchTeam matchTeam : matchTeams) {
            updateTableTeam(matchTeam);
        }
    }

    private void updateTableTeam(MatchTeam newMatchTeam) {
        String teamName = newMatchTeam.getTeamName();
        String season = newMatchTeam.getSeason();
        TypedQuery<TableTeam> query = em.createQuery(
                "SELECT tt FROM TableTeam tt WHERE tt.teamName = :teamName and tt.season = :season", TableTeam.class);
        TableTeam tableTeam = query
                .setParameter("teamName", teamName)
                .setParameter("season", season)
                .getResultStream()
                .findFirst()
                .orElse(null);
        if (tableTeam == null) {
            tableTeam = new TableTeam(
                    teamName,
                    newMatchTeam.getWon(),
                    newMatchTeam.getDrawn(),
                    newMatchTeam.getLost(),
                    newMatchTeam.getGoalsFor(),
                    newMatchTeam.getGoalsAgainst(),
                    newMatchTeam.getGoalsDifference(),
                    newMatchTeam.getPoints(),
                    season);
        } else {
            tableTeam.setPlayed(tableTeam.getPlayed() + 1);
            tableTeam.setWon(tableTeam.getWon() + newMatchTeam.getWon());
            tableTeam.setDrawn(tableTeam.getDrawn() + newMatchTeam.getDrawn());
            tableTeam.setLost(tableTeam.getLost() + newMatchTeam.getLost());
            tableTeam.setGoalsFor(tableTeam.getGoalsFor() + newMatchTeam.getGoalsFor());
            tableTeam.setGoalsAgainst(tableTeam.getGoalsAgainst() + newMatchTeam.getGoalsAgainst());
            tableTeam.setGoalsDifference(tableTeam.getGoalsDifference() + newMatchTeam.getGoalsDifference());
            tableTeam.setPoints(tableTeam.getPoints() + newMatchTeam.getPoints());
        }
        em.persist(tableTeam);
    }
}
