package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingTableTeamRepository extends CrudRepository<RankingTableTeamEntity, Long> {

    Optional<RankingTableTeamEntity> findByTeamName(String teamName);

    Iterable<RankingTableTeamEntity> findAllByOrderByPointsDesc();

    List<RankingTableTeamEntity> getBySeason(String season);
}
