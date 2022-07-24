package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.repository.MatchRepository;
import io.czen.epldashboardapi.util.MatchConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getMatchesByTeamBySeason(String teamName, String season) {
        return MatchConverter.convertMatchEntities(matchRepository.getMatchesByTeamBySeason(teamName, season));
    }

    public List<Match> getLatestMatchesByTeam(String teamName, int count) {
        return MatchConverter.convertMatchEntities(matchRepository.getLatestMatchesByTeam(teamName, count));
    }

    public List<Match> getMatchesBySeason(String season) {
        return MatchConverter.convertMatchEntities(matchRepository.getBySeasonOrderByDateDesc(season));
    }

    public List<Match> getMatchesByMonthBySeason(String month, String season) {
        LocalDate startDate = generateDate(month, season, true);
        LocalDate endDate = generateDate(month, season, false);
        return MatchConverter.convertMatchEntities(
                matchRepository.getByDateAfterAndDateBeforeOrderByDateDesc(startDate, endDate));
    }

    public List<Match> getMatchesByTeamByMonthBySeason(String teamName, String month, String season) {
        LocalDate startDate = generateDate(month, season, true);
        LocalDate endDate = generateDate(month, season, false);
        return MatchConverter.convertMatchEntities(
                matchRepository.getMatchesByTeamByDateAfterAndDateBeforeOrderByDateDesc(teamName, startDate, endDate));
    }

    private LocalDate generateDate(String month, String season, boolean isStartDate) {
        String[] years = season.split("-");
        int startYear = Integer.parseInt(years[0]);
        int intMonth = Integer.parseInt(month);
        if (isStartDate) {
            return intMonth > 7 ? LocalDate.of(startYear, intMonth, 1).minusDays(1)
                    : LocalDate.of(startYear+1, intMonth, 1).minusDays(1);
        } else {
            if (intMonth == 12) {
                return LocalDate.of(startYear+1, 1, 1);
            } else if (intMonth > 7) {
                return LocalDate.of(startYear, intMonth+1, 1);
            } else {
                return LocalDate.of(startYear+1, intMonth+1, 1);
            }
        }
    }
}
