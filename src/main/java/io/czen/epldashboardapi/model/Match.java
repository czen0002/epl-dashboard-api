package io.czen.epldashboardapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Match {

    @Id
    private long id;
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
}
