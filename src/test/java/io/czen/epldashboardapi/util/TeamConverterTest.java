package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("squid:S5786")
public class TeamConverterTest {

    private final String ARSENAL = "Arsenal";
    private final String CHELSEA = "Chelsea";

    @Test
    public void convertTeamEntitySuccessfully() {
        TeamEntity teamEntity = new TeamEntity(ARSENAL);
        Team team = TeamConverter.convertTeamEntity(teamEntity);

        assertEquals(ARSENAL, team.getTeamName());
    }

    @Test
    public void convertTeamEntitiesSuccessfully() {
        TeamEntity teamEntity1 = new TeamEntity(ARSENAL);
        TeamEntity teamEntity2 = new TeamEntity(CHELSEA);
        List<TeamEntity> teamEntities = Arrays.asList(teamEntity1, teamEntity2);
        List<Team> result = TeamConverter.convertTeamEntities(teamEntities);

        assertEquals(2, result.size());
        assertEquals(ARSENAL, result.get(0).getTeamName());
        assertEquals(CHELSEA, result.get(1).getTeamName());
    }
}
