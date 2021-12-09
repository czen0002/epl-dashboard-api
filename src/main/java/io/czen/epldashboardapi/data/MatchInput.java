package io.czen.epldashboardapi.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchInput {

    private String id;
    private String season;
    private String date;
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
}
