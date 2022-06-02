package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.repository.TeamRepository;
import io.czen.epldashboardapi.util.TeamConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Iterable<TeamEntity> teamEntities = this.teamRepository.findAllByOrderByTeamName();
        List<Team> teams =  new ArrayList<>();
        teamEntities.forEach(t -> teams.add(TeamConverter.convert(t)));
        return teams;
    }

    public Team getTeam(String teamName) {
        TeamEntity teamEntity = this.teamRepository.findByTeamName(teamName).orElse(null);
        if (teamEntity == null) return null;
        return TeamConverter.convert(teamEntity);
    }

//    public TeamEntity getTeamWithMatches(String teamName, int count) {
//        TeamEntity teamEntity = this.teamRepository.findByTeamName(teamName).orElse(null);
//        if (teamEntity != null) teamEntity.setMatchEntities(this.matchService.getLatestMatchesByTeam(teamName, count));
//        return teamEntity;
//    }
}
