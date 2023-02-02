package io.czen.epldashboardapi.util;

import io.czen.epldashboardapi.entity.TeamEntity;
import io.czen.epldashboardapi.model.Team;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamMapperTest {

    private final String ARSENAL = "Arsenal";
    private final TeamMapper teamMapper = new TeamMapper();

    @Test
    public void mapTeamEntitySuccessfully() {
        // Given
        Optional<TeamEntity> optionalTeamEntity = Optional.of(new TeamEntity(ARSENAL));
        // When
        Team result = optionalTeamEntity.map(teamMapper).orElse(null);
        // Then
        assertEquals(ARSENAL, result.getTeamName());
    }
}
