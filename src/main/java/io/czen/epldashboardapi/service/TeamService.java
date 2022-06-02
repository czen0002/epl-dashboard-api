package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final MatchService matchService;

    @Autowired
    public TeamService(TeamRepository teamRepository, MatchService matchService) {
        this.teamRepository = teamRepository;
        this.matchService = matchService;
    }

    public Iterable<TeamEntity> getAllTeamsOrderByTeamName() {
        return this.teamRepository.findAllByOrderByTeamName();
    }

    public TeamEntity getTeam(String teamName) {
        return this.teamRepository.findByTeamName(teamName).orElse(null);
    }

    public TeamEntity getTeamWithMatches(String teamName, int count) {
        TeamEntity teamEntity = this.teamRepository.findByTeamName(teamName).orElse(null);
        if (teamEntity != null) teamEntity.setMatchEntities(this.matchService.getLatestMatchesByTeam(teamName, count));
        return teamEntity;
    }
}
