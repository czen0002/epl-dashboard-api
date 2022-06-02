package io.czen.epldashboardapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public MatchEntity(String homeTeam, String awayTeam, String fullTimeResult) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.fullTimeResult = fullTimeResult;
    }
}
