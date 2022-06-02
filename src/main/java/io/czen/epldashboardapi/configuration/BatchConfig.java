package io.czen.epldashboardapi.configuration;

import io.czen.epldashboardapi.data.JobCompletionNotificationListener;
import io.czen.epldashboardapi.data.MatchInput;
import io.czen.epldashboardapi.data.processor.MatchDataProcessor;
import io.czen.epldashboardapi.data.processor.TableTeamAwayProcessor;
import io.czen.epldashboardapi.data.processor.TableTeamHomeProcessor;
import io.czen.epldashboardapi.model.Match;
import io.czen.epldashboardapi.model.MatchTeam;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
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
                    {setTargetType(MatchInput.class);}
                })
                .build();
    }

    @Bean
    public MatchDataProcessor matchDataProcessor() {
        return new MatchDataProcessor();
    }

    @Bean
    public TableTeamHomeProcessor tableTeamHomeProcessor() {
        return new TableTeamHomeProcessor();
    }

    @Bean
    public TableTeamAwayProcessor tableTeamAwayProcessor() {
        return new TableTeamAwayProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer1(DataSource dataSource) {
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

//    @Bean
//    public JdbcBatchItemWriter<TableTeam> writer2(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<TableTeam>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("")
//                .dataSource(dataSource)
//                .build();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<TableTeam> writer3(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<TableTeam>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("")
//                .dataSource(dataSource)
//                .build();
//    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1, Step step2, Step step3) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

    @Bean
    public Step step1(ItemProcessor<MatchInput, Match> matchDataProcessor, JdbcBatchItemWriter<Match> writer1) {
        return stepBuilderFactory.get("step1")
                .<MatchInput, Match> chunk(10)
                .reader(reader())
                .processor(matchDataProcessor)
                .writer(writer1)
                .build();
    }

    @Bean
    public Step step2(ItemProcessor<MatchInput, MatchTeam> tableTeamHomeProcessor, JdbcBatchItemWriter<MatchTeam> writer2) {
        return stepBuilderFactory.get("step2")
                .<MatchInput, MatchTeam> chunk(10)
                .reader(reader())
                .processor(tableTeamHomeProcessor)
                .writer(writer2)
                .build();
    }

    @Bean
    public Step step3(ItemProcessor<MatchInput, MatchTeam> tableTeamAwayProcessor, JdbcBatchItemWriter<MatchTeam> writer3) {
        return stepBuilderFactory.get("step3")
                .<MatchInput, MatchTeam> chunk(10)
                .reader(reader())
                .processor(tableTeamAwayProcessor)
                .writer(writer3)
                .build();
    }
}
