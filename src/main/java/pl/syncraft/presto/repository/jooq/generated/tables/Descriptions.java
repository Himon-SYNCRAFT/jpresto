/*
 * This file is generated by jOOQ.
 */
package pl.syncraft.presto.repository.jooq.generated.tables;


import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import pl.syncraft.presto.repository.jooq.generated.Indexes;
import pl.syncraft.presto.repository.jooq.generated.Keys;
import pl.syncraft.presto.repository.jooq.generated.Public;
import pl.syncraft.presto.repository.jooq.generated.tables.records.DescriptionsRecord;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


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
public class Descriptions extends TableImpl<DescriptionsRecord> {

    private static final long serialVersionUID = 1170682915;

    /**
     * The reference instance of <code>public.descriptions</code>
     */
    public static final Descriptions DESCRIPTIONS = new Descriptions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DescriptionsRecord> getRecordType() {
        return DescriptionsRecord.class;
    }

    /**
     * The column <code>public.descriptions.id</code>.
     */
    public final TableField<DescriptionsRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("nextval('descriptions_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.descriptions.header</code>.
     */
    public final TableField<DescriptionsRecord, String> HEADER = createField(DSL.name("header"), org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>public.descriptions.text</code>.
     */
    public final TableField<DescriptionsRecord, String> TEXT = createField(DSL.name("text"), org.jooq.impl.SQLDataType.VARCHAR(4000).nullable(false), this, "");

    /**
     * The column <code>public.descriptions.image</code>.
     */
    public final TableField<DescriptionsRecord, String> IMAGE = createField(DSL.name("image"), org.jooq.impl.SQLDataType.VARCHAR(1000), this, "");

    /**
     * The column <code>public.descriptions.type</code>.
     */
    public final TableField<DescriptionsRecord, Integer> TYPE = createField(DSL.name("type"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.descriptions.product_id</code>.
     */
    public final TableField<DescriptionsRecord, Integer> PRODUCT_ID = createField(DSL.name("product_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>public.descriptions</code> table reference
     */
    public Descriptions() {
        this(DSL.name("descriptions"), null);
    }

    /**
     * Create an aliased <code>public.descriptions</code> table reference
     */
    public Descriptions(String alias) {
        this(DSL.name(alias), DESCRIPTIONS);
    }

    /**
     * Create an aliased <code>public.descriptions</code> table reference
     */
    public Descriptions(Name alias) {
        this(alias, DESCRIPTIONS);
    }

    private Descriptions(Name alias, Table<DescriptionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Descriptions(Name alias, Table<DescriptionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Descriptions(Table<O> child, ForeignKey<O, DescriptionsRecord> key) {
        super(child, key, DESCRIPTIONS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.DESCRIPTIONS_PK);
    }

    @Override
    public Identity<DescriptionsRecord, Integer> getIdentity() {
        return Keys.IDENTITY_DESCRIPTIONS;
    }

    @Override
    public UniqueKey<DescriptionsRecord> getPrimaryKey() {
        return Keys.DESCRIPTIONS_PK;
    }

    @Override
    public List<UniqueKey<DescriptionsRecord>> getKeys() {
        return Arrays.<UniqueKey<DescriptionsRecord>>asList(Keys.DESCRIPTIONS_PK);
    }

    @Override
    public List<ForeignKey<DescriptionsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<DescriptionsRecord, ?>>asList(Keys.DESCRIPTIONS__DESCRIPTIONS_IMAGES_FK, Keys.DESCRIPTIONS__DESCRIPTIONS_PRODUCTS_FK);
    }

    public Images images() {
        return new Images(this, Keys.DESCRIPTIONS__DESCRIPTIONS_IMAGES_FK);
    }

    public Products products() {
        return new Products(this, Keys.DESCRIPTIONS__DESCRIPTIONS_PRODUCTS_FK);
    }

    @Override
    public Descriptions as(String alias) {
        return new Descriptions(DSL.name(alias), this);
    }

    @Override
    public Descriptions as(Name alias) {
        return new Descriptions(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Descriptions rename(String name) {
        return new Descriptions(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Descriptions rename(Name name) {
        return new Descriptions(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, String, String, Integer, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
