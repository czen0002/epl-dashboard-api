package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.RankingTableTeam;
import io.czen.epldashboardapi.model.Team;
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
    private final RankingTableTeamService rankingTableTeamService;

    @Autowired
    public TeamController(TeamService teamService, RankingTableTeamService rankingTableTeamService) {
        this.teamService = teamService;
        this.rankingTableTeamService = rankingTableTeamService;
    }

    @GetMapping(value = "/team")
    public List<Team> getAllTeamOrderByTeamName() {
        return teamService.getAllTeamsOrderByTeamName();
    }

    @GetMapping(value = "/team/{teamName}")
    public Team getTeam(@PathVariable String teamName, @RequestParam int count) {
        return teamService.getTeamWithMatches(teamName, count);
    }

    @GetMapping(value = "/table/{season}")
    public List<RankingTableTeam> getRankingTableBySeason(@PathVariable String season) {
        return rankingTableTeamService.getRankingTableBySeason(season);
    }
}
