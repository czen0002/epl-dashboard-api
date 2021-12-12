package io.czen.epldashboardapi.configuration;

import io.czen.epldashboardapi.data.JobCompletionNotificationListener;
import io.czen.epldashboardapi.data.MatchDataProcessor;
import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.model.Match;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final String[] FIELD_NAMES = new String[] { "id", "season", "date", "homeTeam", "awayTeam",
            "fullTimeHomeTeamGoals", "fullTimeAwayTeamGoals", "fullTimeResult", "halfTimeHomeTeamGoals",
            "halfTimeAwayTeamGoals", "halfTimeResult", "referee", "homeTeamShots", "awayTeamShots",
            "homeTeamShotsOnTarget", "awayTeamShotsOnTarget", "homeTeamCorners", "awayTeamCorners",
            "homeTeamFoulsCommitted", "awayTeamFoulsCommitted", "homeTeamYellowCards", "awayTeamYellowCards",
            "homeTeamRedCards", "awayTeamRedCards"};

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("MatchInputReader")
                .resource(new ClassPathResource("match-data.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                    { setTargetType(MatchInput.class);}
                })
                .build();
    }

    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match(id, season, date, home_team, away_team, full_time_home_team_goals, " +
                        "full_time_away_team_goals, full_time_result, half_time_home_team_goals, half_time_away_team_goals, " +
                        "half_time_result, referee, home_team_shots, away_team_shots, home_team_shots_on_target, " +
                        "away_team_shots_on_target, home_team_corners, away_team_corners, home_team_fouls_committed, " +
                        "away_team_fouls_committed, home_team_yellow_cards, away_team_yellow_cards, home_team_red_cards, " +
                        "away_team_red_cards) VALUES (:id, :season, :date, :homeTeam, :awayTeam, :fullTimeHomeTeamGoals," +
                        " :fullTimeAwayTeamGoals, :fullTimeResult, :halfTimeHomeTeamGoals, :halfTimeAwayTeamGoals, " +
                        ":halfTimeResult, :referee, :homeTeamShots, :awayTeamShots, :homeTeamShotsOnTarget, " +
                        ":awayTeamShotsOnTarget, :homeTeamCorners, :awayTeamCorners, :homeTeamFoulsCommitted, " +
                        ":awayTeamFoulsCommitted, :homeTeamYellowCards, :awayTeamYellowCards, :homeTeamRedCards, " +
                        ":awayTeamRedCards)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Match> writer) {
        return stepBuilderFactory.get("step1")
                .<MatchInput, Match> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
