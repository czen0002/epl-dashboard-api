package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.repository.MatchRepository;
import io.czen.epldashboardapi.util.MatchConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getMatchesBySeason(String teamName, String season) {
        return convertMatchEntities(this.matchRepository.getMatchesByTeamBySeason(teamName, season));
    }

    public List<Match> getLatestMatchesByTeam(String teamName, int count) {
        return convertMatchEntities(this.matchRepository.getLatestMatchesByTeam(teamName, count));
    }

    private List<Match> convertMatchEntities(List<MatchEntity> matchEntities) {
        List<Match> matches = new ArrayList<>();
        matchEntities.forEach(m -> matches.add(MatchConverter.convert(m)));
        return matches;
    }

}
