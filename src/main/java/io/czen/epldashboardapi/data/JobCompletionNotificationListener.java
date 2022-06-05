package io.czen.epldashboardapi.data;

import io.czen.epldashboardapi.entity.TeamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            Map<String, TeamEntity> teamData = new HashMap<>();

            em.createQuery("select distinct(m.homeTeam) from Match m", String.class)
                    .getResultList()
                    .stream()
                    .map(TeamEntity::new)
                    .forEach(team -> teamData.put(team.getTeamName(), team));

            teamData.values().forEach(em::persist);
        }
    }
}
