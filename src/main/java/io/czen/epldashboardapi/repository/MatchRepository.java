package io.czen.epldashboardapi.repository;

import io.czen.epldashboardapi.model.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    @Query("select m from Match m where (m.homeTeam=:teamName or m.awayTeam=:teamName) and m.season=:season " +
            "order by date desc")
    List<Match> getMatchesByTeamBySeason(
            @Param("teamName") String teamName,
            @Param("season") String season
    );
}
