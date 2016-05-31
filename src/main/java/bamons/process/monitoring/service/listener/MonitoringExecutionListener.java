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

    /**
     *
     * Job 구동 완료 후 Call
     *
     * @param jobExecution JobExecution 객체
     */
    @AfterJob
    public void executeAfterJob(JobExecution jobExecution) {
        // Job 구동이 실패하였을때
        if(jobExecution.getStatus() == BatchStatus.FAILED) {
            monitoringNotifier.notify(jobExecution);
        }
    }

    public void setMonitoringNotifier(BatchMonitoringNotifier monitoringNotifier) {
       this.monitoringNotifier = monitoringNotifier;
    }
}