package com.dream.application.batch.match.scheduler;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MatchBatchScheduler {

    @Resource(name = "matchJob")
    private final Job matchJob;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "0 0 1 * * ?")
    public void runBatchJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(matchJob, jobParameters);
    }
}
