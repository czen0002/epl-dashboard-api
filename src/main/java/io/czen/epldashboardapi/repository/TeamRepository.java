package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, Long> {

    Iterable<TeamEntity> findAllByOrderByTeamName();

    Optional<TeamEntity> findByTeamName(String teamName);
}
