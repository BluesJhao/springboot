package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Desc ：SpringExecute
 * Created by JHAO on 2018/1/30.
 */
public class SpringExecute {
    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springExecuteConfig/application.xml");
        JobLauncher jobLuncher = (JobLauncher)context.getBean("jobLuncher");
        Job job = (Job)context.getBean("batchJob");
        Job job2 = (Job)context.getBean("chunkJob");
        jobLuncher.run(job,new JobParameters());
//        jobLuncher.run(job2,new JobParameters());
    }
}
