package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.RankingTableTeam;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.repository.TeamRepository;
import io.czen.epldashboardapi.util.TeamConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final MatchService matchService;
    private final RankingTableTeamService rankingTableTeamService;

    @Autowired
    public TeamService(TeamRepository teamRepository, MatchService matchService,
                       RankingTableTeamService rankingTableTeamService) {
        this.teamRepository = teamRepository;
        this.matchService = matchService;
        this.rankingTableTeamService = rankingTableTeamService;
    }

    public List<Team> getAllTeamsOrderByTeamName() {
        return TeamConverter.convertTeamEntities(teamRepository.findAllByOrderByTeamName());
    }

    public Team getTeam(String teamName) {
        TeamEntity teamEntity = teamRepository.findByTeamName(teamName).orElse(null);
        if (teamEntity == null) return null;
        return TeamConverter.convertTeamEntity(teamEntity);
    }

    public Team getTeamWithMatches(String teamName, int count) {
        TeamEntity teamEntity = teamRepository.findByTeamName(teamName).orElse(null);
        if (teamEntity == null) return null;
        Team team = new Team(teamEntity.getTeamName());
        team.setMatches(matchService.getLatestMatchesByTeam(teamName, count));
        return team;
    }

    public List<Team> getTeamsBySeason(String season) {
        List<RankingTableTeam> rankingTableTeams = rankingTableTeamService.getRankingTableBySeason(season);
        List<Team> teams = rankingTableTeams.stream().map(t -> new Team(t.getTeamName())).collect(Collectors.toList());
        Collections.sort(teams, (t1, t2) -> t1.getTeamName().compareTo(t2.getTeamName()));
        return teams;
    }
}
