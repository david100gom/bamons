/*
 *  Copyright 2015 the original author or authors.
 *  @https://github.com/david100gom/Bamons
 * <p/>
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * <p/>
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * <p/>
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import org.springframework.stereotype.Component;

@Component
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
    //@Scheduled(cron = "0 0 1 * * *")
    public void sampleJob() throws Exception {

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
    //@Scheduled(fixedDelay = 1800000)
    public void fileJob() throws Exception {

        try {

            // 실제 json 파일 위치 (예제파일 : test/resources/sample.json)
            String filePath = "/usr/local/*.json";

            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("filePath", filePath).toJobParameters();
            JobExecution execution = jobLauncher.run(restoreFileJob, jobParameters);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
