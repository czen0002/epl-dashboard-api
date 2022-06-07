package io.czen.epldashboardapi.data.writer;

import io.czen.epldashboardapi.entity.MatchEntity;
import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.util.MatchConverter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class MatchWriter implements ItemWriter<Match> {

    private final EntityManager em;

    @Autowired
    public MatchWriter(EntityManager em) {
        this.em = em;
    }

    @Override
    public void write(List<? extends Match> matches) {
        for (Match match : matches) {
            saveMatchEntity(match);
        }
    }

    private void saveMatchEntity(Match match) {
        MatchEntity matchEntity = MatchConverter.convertMatch(match);
        em.persist(matchEntity);
    }
}
