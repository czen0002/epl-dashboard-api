package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SuppressWarnings("squid:S5786")
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
        matchEntity1.setDate(LocalDate.of(2021, 9, 30));
        MatchEntity matchEntity2 = new MatchEntity(ARSENAL, LEEDS, HOME_DRAWN);
        matchEntity2.setSeason(SEASON);
        matchEntity2.setDate(LocalDate.of(2021, 9, 1));
        MatchEntity matchEntity3 = new MatchEntity(ARSENAL, LIVERPOOL, HOME_LOST);
        matchEntity3.setSeason(SEASON);
        matchEntity3.setDate(LocalDate.of(2022, 3, 1));
        MatchEntity matchEntity4 = new MatchEntity(CHELSEA, LIVERPOOL, HOME_DRAWN);
        matchEntity4.setSeason(SEASON);
        matchEntity4.setDate(LocalDate.of(2021, 9, 24));
        Iterable<MatchEntity> iterableMatches = Arrays.asList(matchEntity1, matchEntity2, matchEntity3, matchEntity4);
        matchRepository.saveAll(iterableMatches);
        matchService = new MatchService(matchRepository);
    }

    @AfterAll
    public void cleanUp() {
        matchRepository.deleteAll();
    }

    @Test
    public void shouldGetMatchesByTeamBySeason() {
        List<Match> result = matchService.getMatchesByTeamBySeason(ARSENAL, SEASON);
        assertEquals(3, result.size());
        assertEquals(LocalDate.of(2022, 3, 1), result.get(0).getDate());
    }

    @Test
    public void shouldGetEmptyMatchesBySeason() {
        List<Match> result = matchService.getMatchesByTeamBySeason(EVERTON, SEASON);
        assertEquals(0, result.size());
    }

    @Test
    public void shouldGetTwoLatestMatchesByTeam() {
        List<Match> result = matchService.getLatestMatchesByTeam(ARSENAL, 2);
        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetMatchesBySeason() {
        List<Match> result = matchService.getMatchesBySeason(SEASON);
        assertEquals(4, result.size());
        assertEquals(LocalDate.of(2022, 3, 1), result.get(0).getDate());
    }

    @Test
    public void shouldGetMatchesByMonthBySeason() {
        List<Match> result1 = matchService.getMatchesByMonthBySeason("9", SEASON);
        List<Match> result2 = matchService.getMatchesByMonthBySeason("3", SEASON);
        assertEquals(3, result1.size());
        assertEquals(1, result2.size());
        assertEquals(LocalDate.of(2021, 9, 30),  result1.get(0).getDate());
    }

    @Test
    public void shouldGetMatchesByTeamByMonthBySeason() {
        List<Match> result = matchService.getMatchesByTeamByMonthBySeason(ARSENAL, "9", SEASON);
        assertEquals(2, result.size());
        assertEquals(LocalDate.of(2021, 9, 30), result.get(0).getDate());
    }
}
