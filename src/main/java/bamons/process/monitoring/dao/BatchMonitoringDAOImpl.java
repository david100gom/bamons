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
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BatchMonitoringDAOImpl extends SqlSessionDaoSupport implements BatchMonitoringDAO {

    @Resource(name="sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory factory) {
            super.setSqlSessionFactory(factory);
        }

    /**
     *
     * Job 리스트 정보을 가져온다.
     *
     * @return List<BatchJobExecution> 객체
     * @throws Exception
     */
    @Override
    public List<BatchJobExecution> findBatchJobExecutionList(String start, String limit, String startTime) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("start", start);
        params.put("limit", limit);
        params.put("startTime", startTime);
        return getSqlSession().selectList("batchMonitoringDAO.SELECT_BATCH_JOB_EXECUTION_LIST", params);
    }

    /**
     *
     * Job 리스트 총 카운트
     *
     * @return 총 카운트
     * @throws Exception
     */
    @Override
    public int getBatchJobExecutionTotalCount(String startTime) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("startTime", startTime);
        return getSqlSession().selectOne("batchMonitoringDAO.SELECT_BATCH_JOB_EXECUTION_TOTAL_COUNT", params);
    }

    /**
     *
     * Job 정보를 가져온다.
     *
     * @param jobExecutionId JOB ID
     * @return
     * @throws Exception
     */
    @Override
    public BatchJobExecution getBatchJobExecutionInfo(int jobExecutionId) throws Exception {
        return getSqlSession().selectOne("batchMonitoringDAO.SELECT_BATCH_JOB_EXECUTION_INFO", jobExecutionId);
    }

    /**
     *
     * Step 리스트 정보를 가져온다.
     *
     * @param jobExecutionId JOB ID
     * @return  List<BatchStepExecution> 객체
     * @throws Exception
     */
    @Override
    public List<BatchStepExecution> findBatchStepExecutionList(int jobExecutionId) throws Exception {
        return getSqlSession().selectList("batchMonitoringDAO.SELECT_BATCH_STEP_EXECUTION_LIST", jobExecutionId);
    }
}
