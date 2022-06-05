package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;

import java.util.ArrayList;
import java.util.List;

public final class TeamConverter {

    private TeamConverter() {
        // not called
    }

    public static Team convertTeamEntity(TeamEntity teamEntity) {
        return new Team(teamEntity.getTeamName());
    }

    public static List<Team> convertTeamEntities(Iterable<TeamEntity> teamEntities) {
        List<Team> teams =  new ArrayList<>();
        teamEntities.forEach(t -> teams.add(TeamConverter.convertTeamEntity(t)));
        return teams;
    }
}
