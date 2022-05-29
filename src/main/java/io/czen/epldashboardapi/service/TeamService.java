package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.model.Team;
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

    public Iterable<Team> getAllTeamsOrderByTeamName() {
        return this.teamRepository.findAllByOrderByTeamName();
    }

    public Team getTeam(String teamName) {
        return this.teamRepository.findByTeamName(teamName).orElse(null);
    }

    public Team getTeamWithMatches(String teamName, int count) {
        Team team = this.teamRepository.findByTeamName(teamName).orElse(null);
        if (team != null) team.setMatches(this.matchService.getLatestMatchesByTeam(teamName, count));
        return team;
    }
}
