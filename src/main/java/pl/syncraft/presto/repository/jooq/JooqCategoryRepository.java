package pl.syncraft.presto.repository.jooq;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;
import pl.syncraft.presto.core.filters.CategoryFilter;
import pl.syncraft.presto.core.filters.Filter;
import pl.syncraft.presto.core.entity.Category;
import pl.syncraft.presto.core.repository.CategoryRepository;
import pl.syncraft.presto.repository.jooq.generated.tables.records.CategoriesRecord;
import pl.syncraft.presto.web.Context;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.syncraft.presto.repository.jooq.generated.Tables.CATEGORIES;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/15
 */
public class JooqCategoryRepository implements CategoryRepository {

    @Override
    public Optional<Category> get(Integer id) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        JdbcMapperFactory jdbcMapperFactory = JdbcMapperFactory.newInstance();
        JdbcMapper<Category> mapper = jdbcMapperFactory
                .addKeys("id")
                .newMapper(Category.class);

        ResultSet result = session
                .select(
                    CATEGORIES.ID,
                    CATEGORIES.NAME
                )
                .from(CATEGORIES)
                .where(CATEGORIES.ID.eq(id))
                .fetchResultSet();

        try {
            Stream<Category> stream = mapper.stream(result);
            return stream.findFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> find(CategoryFilter filter) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        JdbcMapperFactory jdbcMapperFactory = JdbcMapperFactory.newInstance();
        JdbcMapper<Category> mapper = jdbcMapperFactory
                .addKeys("id")
                .newMapper(Category.class);

        SelectJoinStep<Record2<Integer, String>> query = session
                .select(
                        CATEGORIES.ID,
                        CATEGORIES.NAME
                )
                .from(CATEGORIES);

        Condition where = DSL.trueCondition();

        if (filter.id != null && filter.id.getValue() != null) {
            Filter.Operator operator = filter.id.getOperator();
            Integer value = filter.id.getValue();

            if (operator == Filter.Operator.EQUAL) {
                where = where.and(CATEGORIES.ID.eq(value));
            } else if (operator == Filter.Operator.NOT_EQUAL) {
                where = where.and(CATEGORIES.ID.notEqual(value));
            } else if (operator == Filter.Operator.GREATER) {
                where = where.and(CATEGORIES.ID.greaterThan(value));
            } else if (operator == Filter.Operator.LESSER) {
                where = where.and(CATEGORIES.ID.lessThan(value));
            } else if (operator == Filter.Operator.GREATER_OR_EQUAL) {
                where = where.and(CATEGORIES.ID.greaterOrEqual(value));
            } else if (operator == Filter.Operator.LESSER_OR_EQUAL) {
                where = where.and(CATEGORIES.ID.lessOrEqual(value));
            }
        }

        if (filter.name != null && filter.name.getValue() != null) {
            Filter.Operator operator = filter.name.getOperator();
            String value = filter.name.getValue();

            if (operator == Filter.Operator.LIKE) {
                where = where.and(CATEGORIES.NAME.like(value));
            } else if (operator == Filter.Operator.NOT_LIKE) {
                where = where.and(CATEGORIES.NAME.notLike(value));
            }
        }

        ResultSet result = query.where(where)
                .orderBy(CATEGORIES.ID)
                .fetchResultSet();

        try {
            Stream<Category> stream = mapper.stream(result);
            return stream.collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category save(Category category) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        if (category.getId() == null) {
            CategoriesRecord record = session
                    .insertInto(
                        CATEGORIES,
                        CATEGORIES.ID,
                        CATEGORIES.NAME
                    ).values(
                        category.getId(),
                        category.getName()
                    )
                    .returning(CATEGORIES.ID)
                    .fetchOne();

            category.setId(record.getId());
        } else {
            session.update(CATEGORIES)
                    .set(CATEGORIES.NAME, category.getName())
                    .where(CATEGORIES.ID.eq(category.getId()))
                    .execute();
        }

        return category;
    }

    @Override
    public void remove(Integer id) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        session.delete(CATEGORIES)
                .where(CATEGORIES.ID.eq(id))
                .execute();
    }
}
