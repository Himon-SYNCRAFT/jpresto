package pl.syncraft.presto.repository.jooq;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record10;
import org.jooq.SelectOnConditionStep;
import org.jooq.impl.DSL;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;
import pl.syncraft.presto.core.filters.Filter;
import pl.syncraft.presto.core.filters.ProductFilter;
import pl.syncraft.presto.core.entity.Category;
import pl.syncraft.presto.core.entity.Image;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.repository.jooq.generated.tables.records.ProductsRecord;
import pl.syncraft.presto.web.Context;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.syncraft.presto.repository.jooq.generated.Tables.*;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/13
 */
public class JooqProductRepository implements ProductRepository {
    @Override
    public Optional<Product> get(Integer id) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        JdbcMapperFactory jdbcMapperFactory = JdbcMapperFactory.newInstance();
        JdbcMapper<Product> mapper = jdbcMapperFactory
                .addKeys("id", "category_id", "image_url", "descriptionitem_id")
                .newMapper(Product.class);

        ResultSet result = session
                .select(
                        PRODUCTS.ID,
                        PRODUCTS.NAME,
                        PRODUCTS.SKU,
                        CATEGORIES.ID.as("category_id"),
                        CATEGORIES.NAME.as("category_name"),
                        IMAGES.URL.as("image_url"),
                        DESCRIPTIONS.ID.as("descriptionitem_id"),
                        DESCRIPTIONS.HEADER.as("descriptionitem_header"),
                        DESCRIPTIONS.IMAGE.as("descriptionitem_image"),
                        DESCRIPTIONS.TEXT.as("descriptionitem_text")
                )
                .from(PRODUCTS)
                .leftJoin(PRODUCT_IMAGES).on(PRODUCT_IMAGES.PRODUCT_ID.eq(PRODUCTS.ID))
                .leftJoin(IMAGES).on(IMAGES.URL.eq(PRODUCT_IMAGES.IMAGE))
                .leftJoin(PRODUCT_CATEGORIES).on(PRODUCT_CATEGORIES.PRODUCT_ID.eq(PRODUCTS.ID))
                .leftJoin(CATEGORIES).on(CATEGORIES.ID.eq(PRODUCT_CATEGORIES.CATEGORY_ID))
                .leftJoin(DESCRIPTIONS).on(DESCRIPTIONS.ID.eq(PRODUCTS.ID))
                .where(PRODUCTS.ID.eq(id))
                .orderBy(PRODUCTS.ID)
                .fetchResultSet();

        try {
            Stream<Product> stream = mapper.stream(result);
            return stream.findFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> find(ProductFilter filter) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        JdbcMapperFactory jdbcMapperFactory = JdbcMapperFactory.newInstance();
        JdbcMapper<Product> mapper = jdbcMapperFactory
                .addKeys("id", "category_id", "image_url", "descriptionitem_id")
                .newMapper(Product.class);

        SelectOnConditionStep<Record10<Integer, String, String, Integer, String, String, Integer, String, String, String>> query = session
                .select(
                        PRODUCTS.ID,
                        PRODUCTS.NAME,
                        PRODUCTS.SKU,
                        CATEGORIES.ID.as("category_id"),
                        CATEGORIES.NAME.as("category_name"),
                        IMAGES.URL.as("image_url"),
                        DESCRIPTIONS.ID.as("descriptionitem_id"),
                        DESCRIPTIONS.HEADER.as("descriptionitem_header"),
                        DESCRIPTIONS.IMAGE.as("descriptionitem_image"),
                        DESCRIPTIONS.TEXT.as("descriptionitem_text")
                )
                .from(PRODUCTS)
                .leftJoin(PRODUCT_IMAGES).on(PRODUCT_IMAGES.PRODUCT_ID.eq(PRODUCTS.ID))
                .leftJoin(IMAGES).on(IMAGES.URL.eq(PRODUCT_IMAGES.IMAGE))
                .leftJoin(PRODUCT_CATEGORIES).on(PRODUCT_CATEGORIES.PRODUCT_ID.eq(PRODUCTS.ID))
                .leftJoin(CATEGORIES).on(CATEGORIES.ID.eq(PRODUCT_CATEGORIES.CATEGORY_ID))
                .leftJoin(DESCRIPTIONS).on(DESCRIPTIONS.PRODUCT_ID.eq(PRODUCTS.ID));

        Condition where = DSL.trueCondition();

        if (filter.id != null && filter.id.getValue() != null) {
            Filter.Operator operator = filter.id.getOperator();
            Integer value = filter.id.getValue();

            if (operator == Filter.Operator.EQUAL) {
                where = where.and(PRODUCTS.ID.eq(value));
            } else if (operator == Filter.Operator.NOT_EQUAL) {
                where = where.and(PRODUCTS.ID.notEqual(value));
            } else if (operator == Filter.Operator.GREATER) {
                where = where.and(PRODUCTS.ID.greaterThan(value));
            } else if (operator == Filter.Operator.LESSER) {
                where = where.and(PRODUCTS.ID.lessThan(value));
            } else if (operator == Filter.Operator.GREATER_OR_EQUAL) {
                where = where.and(PRODUCTS.ID.greaterOrEqual(value));
            } else if (operator == Filter.Operator.LESSER_OR_EQUAL) {
                where = where.and(PRODUCTS.ID.lessOrEqual(value));
            }
        }

        if (filter.name != null && filter.name.getValue() != null) {
            Filter.Operator operator = filter.name.getOperator();
            String value = filter.name.getValue();

            if (operator == Filter.Operator.LIKE) {
                where = where.and(PRODUCTS.NAME.like(value));
            } else if (operator == Filter.Operator.NOT_LIKE) {
                where = where.and(PRODUCTS.NAME.notLike(value));
            }
        }

        if (filter.sku != null && filter.sku.getValue() != null) {
            Filter.Operator operator = filter.sku.getOperator();
            String value = filter.sku.getValue();

            if (operator == Filter.Operator.LIKE) {
                where = where.and(PRODUCTS.SKU.like(value));
            } else if (operator == Filter.Operator.NOT_LIKE) {
                where = where.and(PRODUCTS.SKU.notLike(value));
            }
        }

        ResultSet result = query.where(where)
                .orderBy(PRODUCTS.ID)
                .fetchResultSet();

        try {
            Stream<Product> stream = mapper.stream(result);
            return stream.collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product save(Product product) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        if (product.getId() == null) {
            ProductsRecord record = session.insertInto(
                    PRODUCTS,
                    PRODUCTS.NAME,
                    PRODUCTS.SKU
            ).values(
                    product.getName(),
                    product.getSku()
            )
                    .returning(PRODUCTS.ID)
                    .fetchOne();

            product.setId(record.getId());
        } else {
            session.update(PRODUCTS)
                    .set(PRODUCTS.NAME, product.getName())
                    .set(PRODUCTS.SKU, product.getSku())
                    .where(PRODUCTS.ID.eq(product.getId()))
                    .execute();
        }

        return product;
    }

    @Override
    public void remove(Integer id) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        session.delete(PRODUCTS)
                .where(PRODUCTS.ID.eq(id))
                .execute();
    }

    @Override
    public void addCategory(Product product, Category category) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        session.insertInto(
                PRODUCT_CATEGORIES,
                PRODUCT_CATEGORIES.PRODUCT_ID,
                PRODUCT_CATEGORIES.CATEGORY_ID
        ).values(
                product.getId(),
                category.getId()
        )
        .execute();
    }

    @Override
    public void removeCategory(Product product, Category category) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        session.delete(PRODUCT_CATEGORIES)
                .where(
                        PRODUCT_CATEGORIES.PRODUCT_ID.eq(product.getId())
                        .and(PRODUCT_CATEGORIES.CATEGORY_ID.eq(category.getId()))
                )
                .execute();
    }

    @Override
    public void addImage(Product product, Image image) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        session.insertInto(
                PRODUCT_IMAGES,
                PRODUCT_IMAGES.PRODUCT_ID,
                PRODUCT_IMAGES.IMAGE
            )
            .values(
                product.getId(),
                image.getUrl()
            )
            .execute();
    }

    @Override
    public void removeImage(Product product, Image image) {
        DSLContext session = (DSLContext) Context.getTransactionManager().getSession();

        session.delete(PRODUCT_IMAGES)
                .where(
                        PRODUCT_IMAGES.PRODUCT_ID.eq(product.getId())
                                .and(PRODUCT_IMAGES.IMAGE.eq(image.getUrl()))
                )
                .execute();
    }
}
