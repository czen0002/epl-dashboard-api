package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamConverterTest {

    @Test
    public void convertTeamSuccessfully() {
        TeamEntity teamEntity = new TeamEntity("Arsenal");
        Team team = TeamConverter.convert(teamEntity);

        assertEquals("Arsenal", team.getTeamName());
    }
}
