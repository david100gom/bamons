package bamons.process.batch.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Platform Development Center - NMP Corp.
 *
 * @author David KIM
 * @since 1.0
 */
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
