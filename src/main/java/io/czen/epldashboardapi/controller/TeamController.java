package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.Team;
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
@RequestMapping(value = "/v1/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeamOrderByTeamName() {
        return teamService.getAllTeamsOrderByTeamName();
    }

    @GetMapping(value = "/{teamName}")
    public Team getTeam(@PathVariable String teamName, @RequestParam int count) {
        return teamService.getTeamWithMatches(teamName, count);
    }

    @GetMapping(value = "/season/{season}")
    public List<Team> getTeamsBySeason(@PathVariable String season) {
        return teamService.getTeamsBySeason(season);
    }

    @GetMapping(value = "/seasons/{teamName}")
    public Team getTeamWithSeasons(@PathVariable String teamName) {
        return teamService.getTeamWithSeasons(teamName);
    }
}
