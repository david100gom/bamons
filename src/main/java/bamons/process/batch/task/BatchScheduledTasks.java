/*
 *  Copyright 2015 the original author or authors.
 *  @https://github.com/david100gom/Bamons
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package bamons.process.batch.task;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

public class BatchScheduledTasks {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("sampleJob")
    private Job sampleJob;

    @Autowired
    @Qualifier("restoreFileJob")
    private Job restoreFileJob;

    // 새벽 1시에 구동
    @Scheduled(cron = "0 0 1 * * *")
    public void batchJob() throws Exception {

        try {

            DateTime dt = new DateTime();
            dt = dt.minusDays(1);
            String targetDate = dt.toString(DateTimeFormat.forPattern("yyyy-MM-dd"));

            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("targetDate", targetDate).toJobParameters();
            JobExecution execution = jobLauncher.run(sampleJob, jobParameters);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 30분 마다 실행
    @Scheduled(fixedDelay = 1800000)
    public void batchJob2() throws Exception {

        try {

            String filePath = "/usr/local/*.json";

            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("filePath", filePath).toJobParameters();
            JobExecution execution = jobLauncher.run(restoreFileJob, jobParameters);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
