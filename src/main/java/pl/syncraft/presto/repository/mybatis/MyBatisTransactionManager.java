package pl.syncraft.presto.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import pl.syncraft.presto.TransactionManager;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/12
 */
public class MyBatisTransactionManager implements TransactionManager<SqlSession> {
    private static SqlSession session;
    private static UUID id;

    public MyBatisTransactionManager() {
        if (session == null) {
            try {
                session = MyBatisUtil.buildSessionFactory().openSession();
                id = UUID.randomUUID();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("SessionId: " + id);
    }

    @Override
    public SqlSession getSession() {
        if (session == null) {
            throw new RuntimeException("Session not in progress");
        }
        return session;
    }

    @Override
    public void close() {
        try {
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
            session = null;
        }
    }
}
