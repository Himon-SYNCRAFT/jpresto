/*
 * This file is generated by jOOQ.
 */
package pl.syncraft.presto.repository.jooq.generated.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.UpdatableRecordImpl;
import pl.syncraft.presto.repository.jooq.generated.tables.Images;

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
public class ImagesRecord extends UpdatableRecordImpl<ImagesRecord> implements Record1<String> {

    private static final long serialVersionUID = 330448627;

    /**
     * Setter for <code>public.images.url</code>.
     */
    public void setUrl(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.images.url</code>.
     */
    public String getUrl() {
        return (String) get(0);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row1<String> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    @Override
    public Row1<String> valuesRow() {
        return (Row1) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Images.IMAGES.URL;
    }

    @Override
    public String component1() {
        return getUrl();
    }

    @Override
    public String value1() {
        return getUrl();
    }

    @Override
    public ImagesRecord value1(String value) {
        setUrl(value);
        return this;
    }

    @Override
    public ImagesRecord values(String value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ImagesRecord
     */
    public ImagesRecord() {
        super(Images.IMAGES);
    }

    /**
     * Create a detached, initialised ImagesRecord
     */
    public ImagesRecord(String url) {
        super(Images.IMAGES);

        set(0, url);
    }
}
