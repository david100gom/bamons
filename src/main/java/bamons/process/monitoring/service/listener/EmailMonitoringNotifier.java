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

import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class EmailMonitoringNotifier implements BatchMonitoringNotifier {

    private String formatExceptionMessage(Throwable exception) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        exception.printStackTrace(new PrintStream(baos));
        return baos.toString();
    }

    private String createMessageContent(JobExecution jobExecution) {

        List<Throwable> exceptions = jobExecution.getFailureExceptions();
        StringBuilder content = new StringBuilder();
        content.append("Job execution ID #");
        content.append(jobExecution.getId());
        content.append(" of job instance ID #");
        content.append(jobExecution.getJobInstance().getId());
        content.append(" failed with following exceptions : ");

        for (Throwable exception : exceptions) {
            content.append("");
            content.append(formatExceptionMessage(exception));
        }
        return content.toString();
    }

    public void notify(JobExecution jobExecution) {

        String content = createMessageContent(jobExecution);

        try {
            // 메일 전송 로직 추가.
        } catch (Exception ex) {

        }
    }
}