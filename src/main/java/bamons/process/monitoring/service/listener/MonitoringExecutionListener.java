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
package bamons.process.monitoring.service.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

public class MonitoringExecutionListener {

    private BatchMonitoringNotifier monitoringNotifier;

    @BeforeJob
    public void executeBeforeJob(JobExecution jobExecution) {
    }

    @AfterJob
    public void executeAfterJob(JobExecution jobExecution) {
        // JOB 구동이 실패하였을때
        if(jobExecution.getStatus() == BatchStatus.FAILED) {
            monitoringNotifier.notify(jobExecution);
        }
    }

    public void setMonitoringNotifier(BatchMonitoringNotifier monitoringNotifier) {
       this.monitoringNotifier = monitoringNotifier;
    }
}