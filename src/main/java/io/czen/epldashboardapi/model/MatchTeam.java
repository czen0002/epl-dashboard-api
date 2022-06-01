package io.czen.epldashboardapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchTeam {

    private String teamName;
    private Integer won;
    private Integer drawn;
    private Integer lost;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalsDifference;
    private Integer points;
    private String season;

    public MatchTeam(Integer won, Integer drawn, Integer lost, Integer goalsFor, Integer goalsAgainst,
                     Integer goalsDifference, Integer points) {
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalsDifference = goalsDifference;
        this.points = points;
    }
}
