package io.czen.epldashboardapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingTableTeam {

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
}
