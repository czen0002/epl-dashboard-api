package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    @Query("select m from Match m where (m.homeTeam=:teamName or m.awayTeam=:teamName) and m.season=:season " +
            "order by date desc")
    List<Match> getMatchesByTeamBySeason(
            @Param("teamName") String teamName,
            @Param("season") String season
    );

    List<Match> getByHomeTeamOrAwayTeamOrderByDateDesc(@Param("teamName") String homeTeam, String awayTeam, Pageable pageable);

    default List<Match> getLatestMatchesByTeam(String teamName, int count) {
        return getByHomeTeamOrAwayTeamOrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
