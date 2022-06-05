package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.repository.MatchRepository;
import io.czen.epldashboardapi.util.MatchConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getMatchesBySeason(String teamName, String season) {
        return MatchConverter.convertMatchEntities(this.matchRepository.getMatchesByTeamBySeason(teamName, season));
    }

    public List<Match> getLatestMatchesByTeam(String teamName, int count) {
        return MatchConverter.convertMatchEntities(this.matchRepository.getLatestMatchesByTeam(teamName, count));
    }
}
