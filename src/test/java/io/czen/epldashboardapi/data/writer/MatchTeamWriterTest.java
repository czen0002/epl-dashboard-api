package io.czen.epldashboardapi.data.writer;

import io.czen.epldashboardapi.model.MatchTeam;
import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
import io.czen.epldashboardapi.repository.TableTeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MatchTeamWriterTest {

    private final String SEASON = "2021-22";
    private final String ARSENAL = "Arsenal";
    private final String LEEDS = "Leeds";

    private final EntityManager entityManager;
    private final TableTeamRepository tableTeamRepository;

    @Autowired
    public MatchTeamWriterTest(
            EntityManager entityManager,
            TableTeamRepository tableTeamRepository) {
        this.entityManager = entityManager;
        this.tableTeamRepository = tableTeamRepository;
    }

    @Test
    public void writeSuccessfully() {
        MatchTeamWriter matchTeamHomeWriter = new MatchTeamWriter(entityManager);
        MatchTeam matchTeam1 = new MatchTeam(1, 0, 0, 3, 2, 1, 3);
        matchTeam1.setTeamName(ARSENAL);
        matchTeam1.setSeason(SEASON);
        MatchTeam matchTeam2 = new MatchTeam(0, 1, 0, 0, 0, 0, 1);
        matchTeam2.setTeamName(ARSENAL);
        matchTeam2.setSeason(SEASON);
        MatchTeam matchTeam3 = new MatchTeam(0, 0, 1, 1, 2, -1, 0);
        matchTeam3.setTeamName(ARSENAL);
        matchTeam3.setSeason(SEASON);
        MatchTeam matchTeam4 = new MatchTeam(0, 1, 0, 2, 2, 0, 1);
        matchTeam4.setTeamName(LEEDS);
        matchTeam4.setSeason(SEASON);
        List<MatchTeam> matchTeams = new ArrayList<>(List.of(matchTeam1, matchTeam2, matchTeam3, matchTeam4));
        matchTeamHomeWriter.write(matchTeams);

        RankingTableTeamEntity rankingTableTeamEntity1 = tableTeamRepository.findByTeamName(ARSENAL).get();
        assertEquals(Integer.valueOf(4), rankingTableTeamEntity1.getPoints());
        assertEquals(Integer.valueOf(3), rankingTableTeamEntity1.getPlayed());
        assertEquals(Integer.valueOf(0), rankingTableTeamEntity1.getGoalsDifference());
        RankingTableTeamEntity rankingTableTeamEntity2 = tableTeamRepository.findByTeamName(LEEDS).get();
        assertEquals(Integer.valueOf(1), rankingTableTeamEntity2.getPoints());
        assertEquals(Integer.valueOf(1), rankingTableTeamEntity2.getPlayed());
        assertEquals(Integer.valueOf(0), rankingTableTeamEntity2.getGoalsDifference());

        Iterable<RankingTableTeamEntity> tableTeams = tableTeamRepository.findAllByOrderByPointsDesc();
        List<RankingTableTeamEntity> rankingTableTeamsListEntity = new ArrayList<>();
        tableTeams.forEach(rankingTableTeamsListEntity::add);
        assertEquals(2, rankingTableTeamsListEntity.size());
        assertEquals(ARSENAL, rankingTableTeamsListEntity.get(0).getTeamName());
        assertEquals(LEEDS, rankingTableTeamsListEntity.get(1).getTeamName());
    }
}
