package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Iterable<Team> getAllTeams() {
        return this.teamRepository.findAllByOrderByTeamName();
    }

    public Team getTeam(String teamName) {
        return this.teamRepository.findByTeamName(teamName).orElse(null);
    }
}
