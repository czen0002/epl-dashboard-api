package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    Iterable<Team> findAllByOrderByTeamName();

    Optional<Team> findByTeamName(String teamName);
}
