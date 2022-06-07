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
            saveRankingTableTeamEntity(matchTeam);
        }
    }

    private void saveRankingTableTeamEntity(MatchTeam matchTeam) {
        String teamName = matchTeam.getTeamName();
        String season = matchTeam.getSeason();
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
                    matchTeam.getWon(),
                    matchTeam.getDrawn(),
                    matchTeam.getLost(),
                    matchTeam.getGoalsFor(),
                    matchTeam.getGoalsAgainst(),
                    matchTeam.getGoalsDifference(),
                    matchTeam.getPoints(),
                    season);
        } else {
            rankingTableTeamEntity.setPlayed(rankingTableTeamEntity.getPlayed() + 1);
            rankingTableTeamEntity.setWon(rankingTableTeamEntity.getWon() + matchTeam.getWon());
            rankingTableTeamEntity.setDrawn(rankingTableTeamEntity.getDrawn() + matchTeam.getDrawn());
            rankingTableTeamEntity.setLost(rankingTableTeamEntity.getLost() + matchTeam.getLost());
            rankingTableTeamEntity.setGoalsFor(rankingTableTeamEntity.getGoalsFor() + matchTeam.getGoalsFor());
            rankingTableTeamEntity.setGoalsAgainst(rankingTableTeamEntity.getGoalsAgainst() + matchTeam.getGoalsAgainst());
            rankingTableTeamEntity.setGoalsDifference(rankingTableTeamEntity.getGoalsDifference() + matchTeam.getGoalsDifference());
            rankingTableTeamEntity.setPoints(rankingTableTeamEntity.getPoints() + matchTeam.getPoints());
        }
        em.persist(rankingTableTeamEntity);
    }
}
