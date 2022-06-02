package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.entity.MatchEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<MatchEntity, Long> {

    @Query("select m from MatchEntity m where (m.homeTeam=:teamName or m.awayTeam=:teamName) and m.season=:season " +
            "order by date desc")
    List<MatchEntity> getMatchesByTeamBySeason(
            @Param("teamName") String teamName,
            @Param("season") String season
    );

    List<MatchEntity> getByHomeTeamOrAwayTeamOrderByDateDesc(@Param("teamName") String homeTeam, String awayTeam, Pageable pageable);

    default List<MatchEntity> getLatestMatchesByTeam(String teamName, int count) {
        return getByHomeTeamOrAwayTeamOrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
