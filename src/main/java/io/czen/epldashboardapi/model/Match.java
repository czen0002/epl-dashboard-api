package io.czen.epldashboardapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Match {

    private String season;
    private LocalDate date;
    private String homeTeam;
    private String awayTeam;
    private String fullTimeHomeTeamGoals;
    private String fullTimeAwayTeamGoals;
    private String fullTimeResult;
    private String halfTimeHomeTeamGoals;
    private String halfTimeAwayTeamGoals;
    private String halfTimeResult;
    private String referee;
    private String homeTeamShots;
    private String awayTeamShots;
    private String homeTeamShotsOnTarget;
    private String awayTeamShotsOnTarget;
    private String homeTeamCorners;
    private String awayTeamCorners;
    private String homeTeamFoulsCommitted;
    private String awayTeamFoulsCommitted;
    private String homeTeamYellowCards;
    private String awayTeamYellowCards;
    private String homeTeamRedCards;
    private String awayTeamRedCards;

    public Match(String homeTeam, String awayTeam, String fullTimeResult) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.fullTimeResult = fullTimeResult;
    }
}
