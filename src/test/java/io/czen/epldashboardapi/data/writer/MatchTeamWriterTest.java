package io.czen.epldashboardapi.data.writer;

import io.czen.epldashboardapi.model.MatchTeam;
import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
import io.czen.epldashboardapi.repository.RankingTableTeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@SuppressWarnings("squid:S5786")
public class MatchTeamWriterTest {

    private final String SEASON = "2021-22";
    private final String ARSENAL = "Arsenal";
    private final String LEEDS = "Leeds";

    private final EntityManager entityManager;
    private final RankingTableTeamRepository rankingTableTeamRepository;

    @Autowired
    public MatchTeamWriterTest(
            EntityManager entityManager,
            RankingTableTeamRepository rankingTableTeamRepository) {
        this.entityManager = entityManager;
        this.rankingTableTeamRepository = rankingTableTeamRepository;
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
        List<MatchTeam> matchTeams = Arrays.asList(matchTeam1, matchTeam2, matchTeam3, matchTeam4);
        matchTeamHomeWriter.write(matchTeams);

        List<RankingTableTeamEntity> result = rankingTableTeamRepository.getBySeasonOrderByPointsDesc(SEASON);

        assertEquals(2, result.size());
        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(4, result.get(0).getPoints());
        assertEquals(0, result.get(1).getGoalsDifference());
    }
}
