package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    public List<Team> findAllByOrderByTeamName();
}
