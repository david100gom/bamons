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
