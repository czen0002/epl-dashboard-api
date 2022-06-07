package io.czen.epldashboardapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class RankingTableTeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private Integer played;
    private Integer won;
    private Integer drawn;
    private Integer lost;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalsDifference;
    private Integer points;
    private String season;

    public RankingTableTeamEntity(String teamName, Integer played, String season) {
        this.teamName = teamName;
        this.played = played;
        this.season = season;
    }

    public RankingTableTeamEntity(String teamName, Integer played, Integer won, Integer drawn, Integer lost, Integer goalsFor,
                                  Integer goalsAgainst, Integer goalsDifference, Integer points, String season) {
        this.teamName = teamName;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalsDifference = goalsDifference;
        this.points = points;
        this.season = season;
    }
}
