package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;

public final class TeamConverter {

    private TeamConverter() {
        // not called
    }

    public static Team convert(TeamEntity teamEntity) {
        return new Team(teamEntity.getTeamName());
    }
}
