package com.dream.application.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Profile("batch-match")
@Service
public class BatchRunner implements ApplicationRunner {
    private final JobLauncher jobLauncher;
    private final Job job;

    public BatchRunner(JobLauncher jobLauncher, @Qualifier("matchApiJob") Job job) {
        log.info("JOB NAME : ", job.getName());
        this.jobLauncher = jobLauncher;
        this.job = job;

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        log.info(job.getName());

        jobLauncher.run(job, jobParameters);
    }
}
