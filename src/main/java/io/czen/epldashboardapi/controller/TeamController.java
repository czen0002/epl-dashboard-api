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
public class TeamController {

    private TeamService teamService;
    private MatchService matchService;

    @Autowired
    public TeamController(TeamService teamService, MatchService matchService) {
        this.teamService = teamService;
        this.matchService = matchService;
    }

    @GetMapping(value = "/team")
    public Iterable<Team> getAllTeam() {
        return this.teamService.getAllTeams();
    }

    @GetMapping(value = "/team/{teamName}/matches")
    public List<Match> getMatchesForTeamInSeason(@PathVariable String teamName, @RequestParam String season) {
        return this.matchService.getMatchesBySeason(teamName, season);
    }

    @GetMapping(value = "/team/{teamName}")
    public Team getTeam(@PathVariable String teamName, @RequestParam int count) {
        Team team = this.teamService.getTeam(teamName);
        if (team != null) team.setMatches(this.matchService.getLatestMatchesByTeam(teamName, count));

        return team;
    }
}
