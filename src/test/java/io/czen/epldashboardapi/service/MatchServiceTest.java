package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MatchServiceTest {

    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";
    private final String LEEDS = "Leeds";
    private final String LIVERPOOL = "Liverpool";
    private final String EVERTON = "Everton";
    private final String HOME_WON = "H";
    private final String HOME_DRAWN = "D";
    private final String HOME_LOST = "A";
    private final String SEASON = "2021-22";

    private final MatchRepository matchRepository;
    private MatchService matchService;

    @Autowired
    public MatchServiceTest(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @BeforeAll
    public void setup() {
        MatchEntity matchEntity1 = new MatchEntity(ARSENAL, CHELSEA, HOME_WON);
        matchEntity1.setSeason(SEASON);
        MatchEntity matchEntity2 = new MatchEntity(ARSENAL, LEEDS, HOME_DRAWN);
        matchEntity2.setSeason(SEASON);
        MatchEntity matchEntity3 = new MatchEntity(ARSENAL, LIVERPOOL, HOME_LOST);
        matchEntity3.setSeason(SEASON);
        MatchEntity matchEntity4 = new MatchEntity(CHELSEA, LIVERPOOL, HOME_DRAWN);
        matchEntity4.setSeason(SEASON);
        MatchEntity[] matchEntities = {matchEntity1, matchEntity2, matchEntity3, matchEntity4};
        Iterable<MatchEntity> iterableMatches = Arrays.asList(matchEntities);
        matchRepository.saveAll(iterableMatches);
        matchService = new MatchService(matchRepository);
    }

    @AfterAll
    public void cleanUp() {
        matchRepository.deleteAll();
    }

    @Test
    public void shouldGetMatchesBySeason() {
        List<Match> result = matchService.getMatchesBySeason(ARSENAL, SEASON);
        assertEquals(3, result.size());
    }

    @Test
    public void shouldGetEmptyMatchesBySeason() {
        List<Match> result = matchService.getMatchesBySeason(EVERTON, SEASON);
        assertEquals(0, result.size());
    }

    @Test
    public void shouldGetTwoLatestMatchesByTeam() {
        List<Match> result = matchService.getLatestMatchesByTeam(ARSENAL, 2);
        assertEquals(2, result.size());
    }
}
