package io.czen.epldashboardapi.controller;

import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping(value = "/{season}")
    public List<Match> getMatchesInSeason(@PathVariable String season) {
        return matchService.getMatchesBySeason(season);
    }

    @GetMapping(value = "/{month}/{season}")
    public List<Match> getMatchesInMonthInSeason(@PathVariable String month, @PathVariable String season) {
        return matchService.getMatchesByMonthBySeason(month, season);
    }

    @GetMapping(value = "/team/{teamName}/{season}")
    public List<Match> getMatchesForTeamInSeason(@PathVariable String teamName, @PathVariable String season) {
        return matchService.getMatchesByTeamBySeason(teamName, season);
    }

    @GetMapping(value = "/team/{teamName}/{month}/{season}")
    public List<Match> getMatchesForTeamInMonthInSeason(@PathVariable String teamName, @PathVariable String month,
                                                        @PathVariable String season) {
        return matchService.getMatchesByTeamByMonthBySeason(teamName, month, season);
    }
}
