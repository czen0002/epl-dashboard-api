package io.czen.epldashboardapi.data.writer;

import io.czen.epldashboardapi.model.MatchTeam;
import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
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
    public void write(List<? extends MatchTeam> matchTeams) {
        for (MatchTeam matchTeam : matchTeams) {
            updateRankingTableTeam(matchTeam);
        }
    }

    private void updateRankingTableTeam(MatchTeam newMatchTeam) {
        String teamName = newMatchTeam.getTeamName();
        String season = newMatchTeam.getSeason();
        TypedQuery<RankingTableTeamEntity> query = em.createQuery(
                "SELECT rtt FROM RankingTableTeamEntity rtt WHERE rtt.teamName = :teamName and rtt.season = :season", RankingTableTeamEntity.class);
        RankingTableTeamEntity rankingTableTeamEntity = query
                .setParameter("teamName", teamName)
                .setParameter("season", season)
                .getResultStream()
                .findFirst()
                .orElse(null);
        if (rankingTableTeamEntity == null) {
            rankingTableTeamEntity = new RankingTableTeamEntity(
                    teamName,
                    1,
                    newMatchTeam.getWon(),
                    newMatchTeam.getDrawn(),
                    newMatchTeam.getLost(),
                    newMatchTeam.getGoalsFor(),
                    newMatchTeam.getGoalsAgainst(),
                    newMatchTeam.getGoalsDifference(),
                    newMatchTeam.getPoints(),
                    season);
        } else {
            rankingTableTeamEntity.setPlayed(rankingTableTeamEntity.getPlayed() + 1);
            rankingTableTeamEntity.setWon(rankingTableTeamEntity.getWon() + newMatchTeam.getWon());
            rankingTableTeamEntity.setDrawn(rankingTableTeamEntity.getDrawn() + newMatchTeam.getDrawn());
            rankingTableTeamEntity.setLost(rankingTableTeamEntity.getLost() + newMatchTeam.getLost());
            rankingTableTeamEntity.setGoalsFor(rankingTableTeamEntity.getGoalsFor() + newMatchTeam.getGoalsFor());
            rankingTableTeamEntity.setGoalsAgainst(rankingTableTeamEntity.getGoalsAgainst() + newMatchTeam.getGoalsAgainst());
            rankingTableTeamEntity.setGoalsDifference(rankingTableTeamEntity.getGoalsDifference() + newMatchTeam.getGoalsDifference());
            rankingTableTeamEntity.setPoints(rankingTableTeamEntity.getPoints() + newMatchTeam.getPoints());
        }
        em.persist(rankingTableTeamEntity);
    }
}
