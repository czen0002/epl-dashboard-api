package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Iterable<Team> findAllByOrderByTeamName();

    Optional<Team> findByTeamName(String teamName);
}
