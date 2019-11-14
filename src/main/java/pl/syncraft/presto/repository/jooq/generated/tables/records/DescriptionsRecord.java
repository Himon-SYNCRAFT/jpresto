/*
 * This file is generated by jOOQ.
 */
package pl.syncraft.presto.repository.jooq.generated.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import pl.syncraft.presto.repository.jooq.generated.tables.Descriptions;

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
public class DescriptionsRecord extends UpdatableRecordImpl<DescriptionsRecord> implements Record5<Integer, String, String, String, Integer> {

    private static final long serialVersionUID = 1219837444;

    /**
     * Setter for <code>public.descriptions.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.descriptions.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.descriptions.header</code>.
     */
    public void setHeader(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.descriptions.header</code>.
     */
    public String getHeader() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.descriptions.text</code>.
     */
    public void setText(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.descriptions.text</code>.
     */
    public String getText() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.descriptions.image</code>.
     */
    public void setImage(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.descriptions.image</code>.
     */
    public String getImage() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.descriptions.type</code>.
     */
    public void setType(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.descriptions.type</code>.
     */
    public Integer getType() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, String, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, String, String, String, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Descriptions.DESCRIPTIONS.ID;
    }

    @Override
    public Field<String> field2() {
        return Descriptions.DESCRIPTIONS.HEADER;
    }

    @Override
    public Field<String> field3() {
        return Descriptions.DESCRIPTIONS.TEXT;
    }

    @Override
    public Field<String> field4() {
        return Descriptions.DESCRIPTIONS.IMAGE;
    }

    @Override
    public Field<Integer> field5() {
        return Descriptions.DESCRIPTIONS.TYPE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getHeader();
    }

    @Override
    public String component3() {
        return getText();
    }

    @Override
    public String component4() {
        return getImage();
    }

    @Override
    public Integer component5() {
        return getType();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getHeader();
    }

    @Override
    public String value3() {
        return getText();
    }

    @Override
    public String value4() {
        return getImage();
    }

    @Override
    public Integer value5() {
        return getType();
    }

    @Override
    public DescriptionsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public DescriptionsRecord value2(String value) {
        setHeader(value);
        return this;
    }

    @Override
    public DescriptionsRecord value3(String value) {
        setText(value);
        return this;
    }

    @Override
    public DescriptionsRecord value4(String value) {
        setImage(value);
        return this;
    }

    @Override
    public DescriptionsRecord value5(Integer value) {
        setType(value);
        return this;
    }

    @Override
    public DescriptionsRecord values(Integer value1, String value2, String value3, String value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DescriptionsRecord
     */
    public DescriptionsRecord() {
        super(Descriptions.DESCRIPTIONS);
    }

    /**
     * Create a detached, initialised DescriptionsRecord
     */
    public DescriptionsRecord(Integer id, String header, String text, String image, Integer type) {
        super(Descriptions.DESCRIPTIONS);

        set(0, id);
        set(1, header);
        set(2, text);
        set(3, image);
        set(4, type);
    }
}