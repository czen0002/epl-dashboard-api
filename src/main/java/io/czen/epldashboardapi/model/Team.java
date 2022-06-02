package io.czen.epldashboardapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Team {

    private String teamName;
    private List<Match> matches;

    public Team(String teamName) {
        this.teamName = teamName;
    }
}
