package io.czen.epldashboardapi.service;

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
        Match match1 = new Match(ARSENAL, CHELSEA, HOME_WON);
        match1.setSeason(SEASON);
        Match match2 = new Match(ARSENAL, LEEDS, HOME_DRAWN);
        match2.setSeason(SEASON);
        Match match3 = new Match(ARSENAL, LIVERPOOL, HOME_LOST);
        match3.setSeason(SEASON);
        Match match4 = new Match(CHELSEA, LIVERPOOL, HOME_DRAWN);
        match4.setSeason(SEASON);
        Match[] matches = {match1, match2, match3, match4};
        Iterable<Match> iterableMatches = Arrays.asList(matches);
        matchRepository.saveAll(iterableMatches);
        matchService = new MatchService(matchRepository);
    }

    @AfterAll
    public void cleanUp() {
        matchRepository.deleteAll();
    }

    @Test
    public void shouldGetMatchesBySeason() {
        List<Match> matchesBySeason = matchService.getMatchesBySeason(ARSENAL, SEASON);
        assertEquals(3, matchesBySeason.size());
    }

    @Test
    public void shouldGetEmptyMatchesBySeason() {
        List<Match> matchesBySeason = matchService.getMatchesBySeason(EVERTON, SEASON);
        assertEquals(0, matchesBySeason.size());
    }

    @Test
    public void shouldGetTwoLatestMatchesByTeam() {
        List<Match> latestTwoMatches = matchService.getLatestMatchesByTeam(ARSENAL, 2);
        assertEquals(2, latestTwoMatches.size());
    }
}
