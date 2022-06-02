package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.repository.MatchRepository;
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

    public List<MatchEntity> getMatchesBySeason(String teamName, String season) {
        return this.matchRepository.getMatchesByTeamBySeason(teamName, season);
    }

    public List<MatchEntity> getLatestMatchesByTeam(String teamName, int count) {
        return this.matchRepository.getLatestMatchesByTeam(teamName, count);
    }
}
