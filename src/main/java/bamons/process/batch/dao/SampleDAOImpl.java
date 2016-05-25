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
package bamons.process.batch.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SampleDAOImpl extends SqlSessionDaoSupport implements SampleDAO {

    @Resource(name = "sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory factory) {
        super.setSqlSessionFactory(factory);
    }

    @Override
    public int getTotalCount() throws Exception {
        return getSqlSession().selectOne("sampleDAO.SELECT_TOTAL_COUNT");
    }
}
