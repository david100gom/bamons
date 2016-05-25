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
package bamons.process.monitoring.service;

import bamons.process.monitoring.domain.BatchJobExecution;
import bamons.process.monitoring.domain.BatchStepExecution;

import java.util.List;
import java.util.Map;

public interface BatchMonitoringService {

    // Job 리스트 정보을 가져온다.
    public List<BatchJobExecution> findBatchJobExecutionList(String start, String limit, String startTime) throws Exception;

    // Job 리스트 총 카운트
    public int getBatchJobExecutionTotalCount(String startTime) throws Exception;

    // Job & Step 정보를 보여준다.
    public Map<String, Object> findBatchJobStepExecutionData(int jobExecutionId)  throws Exception;

    // Step 리스트 정보을 가져온다.
    public List<BatchStepExecution>  findBatchStepExecutionList(int jobExecutionId)  throws Exception;

    // Job 을 재구동한다.
    public void jobRestart(int jobExecutionId, String jobName)  throws Exception;

    // Job 을 구동한다.
    public void jobStart(String jobName, String targetDate)  throws Exception;

    // Job 을 중지한다.
    public void jobStop(int jobExecutionId)  throws Exception;

    // rawdata (json file)를 restore 한다.
    public void restoreRawData(String filePath) throws Exception;

}