package io.czen.epldashboardapi.service;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.RankingTableTeam;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.repository.TeamRepository;
import io.czen.epldashboardapi.util.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final MatchService matchService;
    private final RankingTableTeamService rankingTableTeamService;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamService(TeamRepository teamRepository,
                       MatchService matchService,
                       RankingTableTeamService rankingTableTeamService,
                       TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.matchService = matchService;
        this.rankingTableTeamService = rankingTableTeamService;
        this.teamMapper = teamMapper;
    }

    public List<Team> getAllTeamsOrderByTeamName() {
        Iterable<TeamEntity> iteratorTeamEntities = teamRepository.findAllByOrderByTeamName();
        return StreamSupport.stream(
                iteratorTeamEntities.spliterator(), false)
                .map(teamMapper)
                .collect(Collectors.toList());
    }

    public Team getTeam(String teamName) {
        return teamRepository.findByTeamName(teamName).map(teamMapper).orElse(null);
    }

    public Team getTeamWithMatches(String teamName, int count) {
        Team team = teamRepository.findByTeamName(teamName).map(teamMapper).orElse(null);
        if (team == null) return null;
        team.setMatches(matchService.getLatestMatchesByTeam(teamName, count));
        return team;
    }

    public List<Team> getTeamsBySeason(String season) {
        Comparator<Team> teamComparator = Comparator.comparing(Team::getTeamName);
        List<RankingTableTeam> rankingTableTeams = rankingTableTeamService.getRankingTableBySeason(season);
        return rankingTableTeams.stream()
                .map(t -> new Team(t.getTeamName()))
                .sorted(teamComparator)
                .collect(Collectors.toList());
    }

    public Team getTeamWithSeasons(String teamName) {
        Team team = teamRepository.findByTeamName(teamName).map(teamMapper).orElse(null);
        if (team == null) return null;
        team.setSeasons(new ArrayList<>());
        List<RankingTableTeam> rankingTableTeams = rankingTableTeamService.getTeamByTeamNameInSeasons(teamName);
        rankingTableTeams.forEach(t -> team.getSeasons().add(t.getSeason()));
        return team;
    }
}
