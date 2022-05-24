package io.czen.epldashboardapi.data.writer;

import io.czen.epldashboardapi.model.MatchTeam;
import io.czen.epldashboardapi.model.TableTeam;
import io.czen.epldashboardapi.repository.TableTeamRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MatchTeamHomeWriterTest {

    private EntityManager entityManager;
    private TableTeamRepository tableTeamRepository;

    @Autowired
    public MatchTeamHomeWriterTest(
            EntityManager entityManager,
            TableTeamRepository tableTeamRepository) {
        this.entityManager = entityManager;
        this.tableTeamRepository = tableTeamRepository;
    }

    @Test
    public void testFindByTeamName() {
        entityManager.persist(new TableTeam("team1", 2, 3, 1, 12, 4,
                8, 9, "2021-22"));
        entityManager.persist(new TableTeam("team2", 2, 3, 1, 12, 4,
                8, 9, "2021-22"));
        entityManager.persist(new TableTeam("team3", 2, 3, 1, 12, 4,
                8, 9, "2021-22"));
        entityManager.persist(new TableTeam("team4", 2, 3, 1, 12, 4,
                8, 9, "2021-22"));

        Optional<TableTeam> tableTeam = tableTeamRepository.findByTeamName("team1");
        Assert.assertEquals((Integer)2, tableTeam.get().getWon());
    }

    @Test
    public void writeSuccessfully() throws Exception {
        MatchTeamHomeWriter matchTeamHomeWriter = new MatchTeamHomeWriter(entityManager);
        MatchTeam matchTeam1 = new MatchTeam(1, 0, 0, 3, 2, 1, 3);
        matchTeam1.setTeamName("Arsenal");
        matchTeam1.setSeason("2021-22");
        MatchTeam matchTeam2 = new MatchTeam(0, 1, 0, 0, 0, 0, 1);
        matchTeam2.setTeamName("Arsenal");
        matchTeam2.setSeason("2021-22");
        MatchTeam matchTeam3 = new MatchTeam(0, 0, 1, 1, 2, -1, 0);
        matchTeam3.setTeamName("Arsenal");
        matchTeam3.setSeason("2021-22");
        MatchTeam matchTeam4 = new MatchTeam(0, 1, 0, 2, 2, 0, 1);
        matchTeam4.setTeamName("Leeds");
        matchTeam4.setSeason("2021-22");
        List<MatchTeam> matchTeams = new ArrayList<MatchTeam>(List.of(matchTeam1, matchTeam2, matchTeam3, matchTeam4));
        matchTeamHomeWriter.write(matchTeams);

        TableTeam tableTeam1 = tableTeamRepository.findByTeamName("Arsenal").get();
        Assert.assertEquals(Integer.valueOf(4), tableTeam1.getPoints());
        Assert.assertEquals(Integer.valueOf(3), tableTeam1.getPlayed());
        Assert.assertEquals(Integer.valueOf(0), tableTeam1.getGoalsDifference());
        TableTeam tableTeam2 = tableTeamRepository.findByTeamName("Leeds").get();
        Assert.assertEquals(Integer.valueOf(1), tableTeam2.getPoints());
        Assert.assertEquals(Integer.valueOf(1), tableTeam2.getPlayed());
        Assert.assertEquals(Integer.valueOf(0), tableTeam2.getGoalsDifference());

        Iterable<TableTeam> tableTeams = tableTeamRepository.findAllByOrderByPointsDesc();
        List<TableTeam> tableTeamsList = new ArrayList<>();
        tableTeams.forEach(tableTeamsList::add);
        Assert.assertEquals(2, tableTeamsList.size());
        Assert.assertEquals("Arsenal", tableTeamsList.get(0).getTeamName());
        Assert.assertEquals("Leeds", tableTeamsList.get(1).getTeamName());
    }
}
