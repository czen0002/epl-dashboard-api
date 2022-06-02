package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.model.TableTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableTeamRepository extends CrudRepository<TableTeam, Long> {

    Optional<TableTeam> findByTeamName(String teamName);

    Iterable<TableTeam> findAllByOrderByPointsDesc();
}
