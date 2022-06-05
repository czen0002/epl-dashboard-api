package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.repository.TeamRepository;
import io.czen.epldashboardapi.util.TeamConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final MatchService matchService;

    @Autowired
    public TeamService(TeamRepository teamRepository, MatchService matchService) {
        this.teamRepository = teamRepository;
        this.matchService = matchService;
    }

    public List<Team> getAllTeamsOrderByTeamName() {
        return TeamConverter.convertTeamEntities(this.teamRepository.findAllByOrderByTeamName());
    }

    public Team getTeam(String teamName) {
        TeamEntity teamEntity = this.teamRepository.findByTeamName(teamName).orElse(null);
        if (teamEntity == null) return null;
        return TeamConverter.convertTeamEntity(teamEntity);
    }

    public Team getTeamWithMatches(String teamName, int count) {
        TeamEntity teamEntity = this.teamRepository.findByTeamName(teamName).orElse(null);
        if (teamEntity == null) return null;
        Team team = new Team(teamEntity.getTeamName());
        team.setMatches(this.matchService.getLatestMatchesByTeam(teamName, count));
        return team;
    }
}
