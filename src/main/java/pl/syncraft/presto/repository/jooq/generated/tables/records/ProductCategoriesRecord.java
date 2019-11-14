/*
 * This file is generated by jOOQ.
 */
package pl.syncraft.presto.repository.jooq.generated.tables.records;


import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;
import pl.syncraft.presto.repository.jooq.generated.tables.ProductCategories;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProductCategoriesRecord extends UpdatableRecordImpl<ProductCategoriesRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = -1459234865;

    /**
     * Setter for <code>public.product_categories.product_id</code>.
     */
    public void setProductId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.product_categories.product_id</code>.
     */
    public Integer getProductId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.product_categories.category_id</code>.
     */
    public void setCategoryId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.product_categories.category_id</code>.
     */
    public Integer getCategoryId() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ProductCategories.PRODUCT_CATEGORIES.PRODUCT_ID;
    }

    @Override
    public Field<Integer> field2() {
        return ProductCategories.PRODUCT_CATEGORIES.CATEGORY_ID;
    }

    @Override
    public Integer component1() {
        return getProductId();
    }

    @Override
    public Integer component2() {
        return getCategoryId();
    }

    @Override
    public Integer value1() {
        return getProductId();
    }

    @Override
    public Integer value2() {
        return getCategoryId();
    }

    @Override
    public ProductCategoriesRecord value1(Integer value) {
        setProductId(value);
        return this;
    }

    @Override
    public ProductCategoriesRecord value2(Integer value) {
        setCategoryId(value);
        return this;
    }

    @Override
    public ProductCategoriesRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProductCategoriesRecord
     */
    public ProductCategoriesRecord() {
        super(ProductCategories.PRODUCT_CATEGORIES);
    }

    /**
     * Create a detached, initialised ProductCategoriesRecord
     */
    public ProductCategoriesRecord(Integer productId, Integer categoryId) {
        super(ProductCategories.PRODUCT_CATEGORIES);

        set(0, productId);
        set(1, categoryId);
    }
}