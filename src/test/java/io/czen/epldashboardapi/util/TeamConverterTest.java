package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("squid:S5786")
public class TeamConverterTest {

    @Test
    public void convertTeamSuccessfully() {
        TeamEntity teamEntity = new TeamEntity("Arsenal");
        Team team = TeamConverter.convertTeamEntity(teamEntity);

        assertEquals("Arsenal", team.getTeamName());
    }
}
