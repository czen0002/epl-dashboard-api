package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.RankingTableTeam;
import io.czen.epldashboardapi.service.RankingTableTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/table")
public class TableController {

    private final RankingTableTeamService rankingTableTeamService;

    @Autowired
    public TableController(RankingTableTeamService rankingTableTeamService) {
        this.rankingTableTeamService = rankingTableTeamService;
    }

    @GetMapping(value = "/{season}")
    public List<RankingTableTeam> getRankingTableBySeason(@PathVariable String season) {
        return rankingTableTeamService.getRankingTableBySeason(season);
    }
}
