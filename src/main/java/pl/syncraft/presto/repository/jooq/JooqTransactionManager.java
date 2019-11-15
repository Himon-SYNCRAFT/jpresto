package pl.syncraft.presto.repository.jooq;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import pl.syncraft.presto.TransactionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/13
 */
public class JooqTransactionManager implements TransactionManager<DSLContext> {
    private static DSLContext session;
    private static UUID id;

    public JooqTransactionManager(Connection connection) {
        if (session == null) {
            try {
                connection.setAutoCommit(false);
                id = UUID.randomUUID();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            Settings settings = new Settings();
            settings.withRenderFormatted(true);

            session = DSL.using(connection, SQLDialect.POSTGRES, settings);
        }

        System.out.println("SessionId: " + id);
    }

    @Override
    public DSLContext getSession() {
        return session;
    }

    @Override
    public void close() throws Exception {
        Connection connection = session.configuration().connectionProvider().acquire();

        try {
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            connection.close();
            session = null;
        }
    }
}
