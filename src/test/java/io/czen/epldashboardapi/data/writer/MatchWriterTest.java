package io.czen.epldashboardapi.data.writer;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@SuppressWarnings("squid:S5786")
public class MatchWriterTest {

    private final String ARSENAL = "Arsenal";
    private final String LEEDS = "Leeds";
    private final String CHELSEA = "Chelsea";
    private final String LIVERPOOL = "Liverpool";
    private final String HOME_WON = "H";
    private final String HOME_DRAWN = "D";

    private final EntityManager entityManager;
    private final MatchRepository matchRepository;

    @Autowired
    public MatchWriterTest(EntityManager entityManager, MatchRepository matchRepository) {
        this.entityManager = entityManager;
        this.matchRepository = matchRepository;
    }

    @Test
    public void writeSuccessfully() {
        MatchWriter matchWriter = new MatchWriter(entityManager);
        Match match1 = new Match(ARSENAL, CHELSEA, HOME_WON);
        Match match2 = new Match(CHELSEA, LIVERPOOL, HOME_DRAWN);
        Match match3 = new Match(LIVERPOOL, LEEDS, HOME_WON);
        Match match4 = new Match(LEEDS, ARSENAL, HOME_DRAWN);
        List<Match> matches = Arrays.asList(match1, match2, match3, match4);
        matchWriter.write(matches);

        Iterable<MatchEntity> matchEntityIterable = matchRepository.findAll();
        List<MatchEntity> matchEntities = new ArrayList<>();
        matchEntityIterable.forEach(matchEntities::add);

        assertEquals(4, matchEntities.size());
        assertEquals(ARSENAL, matchEntities.get(0).getHomeTeam());
        assertEquals(LIVERPOOL, matchEntities.get(1).getAwayTeam());
        assertEquals(HOME_DRAWN, matchEntities.get(3).getFullTimeResult());
    }
}
