package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
