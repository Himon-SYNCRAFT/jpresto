package pl.syncraft.presto.repository.jooq;

import org.jooq.DSLContext;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;
import pl.syncraft.presto.core.entity.Image;
import pl.syncraft.presto.core.repository.ImageRepository;
import pl.syncraft.presto.web.Context;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

import static pl.syncraft.presto.repository.jooq.generated.Tables.IMAGES;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/15
 */
public class JooqImageRepository implements ImageRepository {
    @Override
    public Optional<Image> get(String url) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        JdbcMapperFactory jdbcMapperFactory = JdbcMapperFactory.newInstance();
        JdbcMapper<Image> mapper = jdbcMapperFactory
                .addKeys("url")
                .newMapper(Image.class);

        ResultSet result = session
                .select(IMAGES.URL)
                .from(IMAGES)
                .where(IMAGES.URL.like(url))
                .orderBy(IMAGES.URL)
                .fetchResultSet();

        try {
            Stream<Image> stream = mapper.stream(result);
            return stream.findFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Image save(Image item) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }
}
