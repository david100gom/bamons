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
package bamons.process.monitoring.dao;

import bamons.process.monitoring.domain.BatchJobExecution;
import bamons.process.monitoring.domain.BatchStepExecution;

import java.util.List;

public interface BatchMonitoringDAO {

    // Job 리스트 정보을 가져온다.
    public List<BatchJobExecution> findBatchJobExecutionList(String start, String limit,String startTime) throws Exception;

    // Job 리스트 총 카운트
    public int getBatchJobExecutionTotalCount(String startTime) throws Exception;

    // Job 정보를 가져온다.
    public BatchJobExecution getBatchJobExecutionInfo(int jobExecutiionId) throws Exception;

    // Step 리스트 정보를 가져온다.
    public List<BatchStepExecution> findBatchStepExecutionList(int jobExecutiionId) throws Exception;
}

