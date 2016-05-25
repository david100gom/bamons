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
package bamons.process.monitoring.domain;

public class BatchStepExecution {

    private int stepExecutionId;        // STEP 실행 ID
    private String stepName;            // STEP 이름
    private int jobExecutionId;         // JOB 실행 ID
    private String startTime;           // 시작 시간
    private String endTime;             // 완료 시간
    private String status;              // 상태
    private String exitCode;            // 결과코드
    private String exitMessage;         // 결과메세지
    private String jobName;             // JOB 이름
    private int commitCount;            // 커밋 수
    private int readCount;              // 읽기 수
    private int filterCount;            // 필터 수
    private int writeCount;             // 저장 수
    private int readSkipCount;          // 읽기 스킵 수
    private int writeSkipCount;         // 저장 스킵 수
    private int processSkipCount;       // 프로세스 스킵 수
    private int rollbackCount;          // 롤백 수

    public int getStepExecutionId() {
        return stepExecutionId;
    }

    public void setStepExecutionId(int stepExecutionId) {
        this.stepExecutionId = stepExecutionId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public int getJobExecutionId() {
        return jobExecutionId;
    }

    public void setJobExecutionId(int jobExecutionId) {
        this.jobExecutionId = jobExecutionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExitCode() {
        return exitCode;
    }

    public void setExitCode(String exitCode) {
        this.exitCode = exitCode;
    }

    public String getExitMessage() {
        return exitMessage;
    }

    public void setExitMessage(String exitMessage) {
        this.exitMessage = exitMessage;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(int commitCount) {
        this.commitCount = commitCount;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getFilterCount() {
        return filterCount;
    }

    public void setFilterCount(int filterCount) {
        this.filterCount = filterCount;
    }

    public int getWriteCount() {
        return writeCount;
    }

    public void setWriteCount(int writeCount) {
        this.writeCount = writeCount;
    }

    public int getReadSkipCount() {
        return readSkipCount;
    }

    public void setReadSkipCount(int readSkipCount) {
        this.readSkipCount = readSkipCount;
    }

    public int getWriteSkipCount() {
        return writeSkipCount;
    }

    public void setWriteSkipCount(int writeSkipCount) {
        this.writeSkipCount = writeSkipCount;
    }

    public int getProcessSkipCount() {
        return processSkipCount;
    }

    public void setProcessSkipCount(int processSkipCount) {
        this.processSkipCount = processSkipCount;
    }

    public int getRollbackCount() {
        return rollbackCount;
    }

    public void setRollbackCount(int rollbackCount) {
        this.rollbackCount = rollbackCount;
    }
}