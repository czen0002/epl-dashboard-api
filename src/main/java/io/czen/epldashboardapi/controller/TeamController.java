package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.entity.TeamEntity;
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
    public List<Team> getAllTeamOrderByTeamName() {
        return this.teamService.getAllTeamsOrderByTeamName();
    }

    @GetMapping(value = "/team/{teamName}/matches")
    public List<MatchEntity> getMatchesForTeamInSeason(@PathVariable String teamName, @RequestParam String season) {
        return this.matchService.getMatchesBySeason(teamName, season);
    }

//    @GetMapping(value = "/team/{teamName}")
//    public TeamEntity getTeam(@PathVariable String teamName, @RequestParam int count) {
//        return this.teamService.getTeamWithMatches(teamName, count);
//    }
}
