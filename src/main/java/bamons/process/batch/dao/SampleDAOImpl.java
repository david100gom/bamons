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
package bamons.process.batch.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SampleDAOImpl extends SqlSessionDaoSupport implements SampleDAO {

    @Resource(name = "sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory factory) {
        super.setSqlSessionFactory(factory);
    }

    /**
     *
     *  특정 날짜의 데이터 건수
     *
     * @param targetDate 날짜
     * @return 건수
     * @throws Exception
     */
    @Override
    public int getTotalCount(String targetDate) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("targetDate", targetDate);
        return getSqlSession().selectOne("sampleDAO.SELECT_TOTAL_COUNT", params);
    }
}