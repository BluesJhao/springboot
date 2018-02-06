package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public PlatformTransactionManager tx() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PlatformTransactionManager tx1() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JobRepositoryFactoryBean jobRepositoryFactoryBean() {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(tx());
        return jobRepositoryFactoryBean;
    }

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
        reader.setResource(new ClassPathResource("springExecuteConfig/sample-data.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }

//    @Bean
//    public ItemReader<Person> reader() {
//        JdbcPagingItemReader reader = new JdbcPagingItemReader();
//        reader.setDataSource(dataSource);
//        reader.setRowMapper(new BeanPropertyRowMapper() {{
//                setMappedClass(hello.Person.class);
//            }
//        });
//        reader.setMaxItemCount(10);
//        reader.setPageSize(2);
//        reader.setQueryProvider(new MySqlPagingQueryProvider(){{
//            setSelectClause("select first_name,last_name,person_id");
//            setFromClause("from people");
//            HashMap hashMap = new HashMap<>();
//            hashMap.put("person_id", Order.ASCENDING);
//            setSortKeys(hashMap);
//        }});
//        return reader;
//    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public Tasklet tasklet() {
        return new Taskletest();
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer() {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
        writer.setDataSource(dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(@Qualifier("step1") Step step, JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(split())
                .next(step4())
//                .flow(step).on("*").to(step2())
                .end()
                .build();
    }

    @Bean
    public Flow split(){
        return new FlowBuilder<Flow>("split").split(taskExecutor()).add(flow1(),flow2()).next(step2()).build();
    }

    @Bean
    public Flow flow1(){
        return new FlowBuilder<Flow>("folw1").start(step1()).next(step2()).build();
    }

    @Bean
    public Flow flow2(){
        return new FlowBuilder<Flow>("flow2").start(step3()).build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer())
//                .taskExecutor(taskExecutor())//multi-thread step execut
                .build();
    }

    @Bean
    public Step step2() {
        new SimpleJob();
        return stepBuilderFactory.get("step2")
                .tasklet(tasklet())
                .build();
    }

    public Step step3() {
        new SimpleJob();
        return stepBuilderFactory.get("step3")
                .tasklet(tasklet())
                .build();
    }

    public Step step4() {
        new SimpleJob();
        return stepBuilderFactory.get("step4")
                .tasklet(tasklet())
                .build();
    }

    // end::jobstep[]
//    @Bean
    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor("spring_batch");
        return new TaskExecutorHolder();
    }
}
