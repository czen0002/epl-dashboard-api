package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.entity.RankingTableTeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingTableTeamRepository extends CrudRepository<RankingTableTeamEntity, Long> {

    List<RankingTableTeamEntity> getBySeasonOrderByPointsDesc(String season);

    List<RankingTableTeamEntity> getByTeamNameOrderBySeasonDesc(String teamName);
}
