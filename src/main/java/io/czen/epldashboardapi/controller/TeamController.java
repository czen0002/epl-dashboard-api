package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.model.Team;
import io.czen.epldashboardapi.service.MatchService;
import io.czen.epldashboardapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1")
public class TeamController {

    private final TeamService teamService;
    private final MatchService matchService;

    @Autowired
    public TeamController(TeamService teamService, MatchService matchService) {
        this.teamService = teamService;
        this.matchService = matchService;
    }

    @GetMapping(value = "/team")
    public Iterable<Team> getAllTeamOrderByTeamName() {
        return this.teamService.getAllTeamsOrderByTeamName();
    }

    @GetMapping(value = "/team/{teamName}/matches")
    public List<Match> getMatchesForTeamInSeason(@PathVariable String teamName, @RequestParam String season) {
        return this.matchService.getMatchesBySeason(teamName, season);
    }

    @GetMapping(value = "/team/{teamName}")
    public Team getTeam(@PathVariable String teamName, @RequestParam int count) {
        return this.teamService.getTeamWithMatches(teamName, count);
    }
}
