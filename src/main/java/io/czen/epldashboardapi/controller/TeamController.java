package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.model.RankingTableTeam;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.service.MatchService;
import io.czen.epldashboardapi.service.RankingTableTeamService;
import io.czen.epldashboardapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1")
public class TeamController {

    private final TeamService teamService;
    private final MatchService matchService;
    private final RankingTableTeamService rankingTableTeamService;

    @Autowired
    public TeamController(TeamService teamService, MatchService matchService,
                          RankingTableTeamService rankingTableTeamService) {
        this.teamService = teamService;
        this.matchService = matchService;
        this.rankingTableTeamService = rankingTableTeamService;
    }

    @GetMapping(value = "/team")
    public List<Team> getAllTeamOrderByTeamName() {
        return teamService.getAllTeamsOrderByTeamName();
    }

    @GetMapping(value = "/team/{teamName}/matches")
    public List<Match> getMatchesForTeamInSeason(@PathVariable String teamName, @RequestParam String season) {
        return matchService.getMatchesBySeason(teamName, season);
    }

    @GetMapping(value = "/team/{teamName}")
    public Team getTeam(@PathVariable String teamName, @RequestParam int count) {
        return teamService.getTeamWithMatches(teamName, count);
    }

    @GetMapping(value = "/table/{season}")
    public List<RankingTableTeam> getRankingTableInSeason(@PathVariable String season) {
        return rankingTableTeamService.getRankingTableTeamsBySeason(season);
    }
}
