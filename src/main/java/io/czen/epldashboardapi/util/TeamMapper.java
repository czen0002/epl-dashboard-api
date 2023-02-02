package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TeamMapper implements Function<TeamEntity, Team> {
    @Override
    public Team apply(TeamEntity teamEntity) {
        return new Team(teamEntity.getTeamName());
    }
}
