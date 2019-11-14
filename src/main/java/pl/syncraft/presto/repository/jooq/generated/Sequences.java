/*
 * This file is generated by jOOQ.
 */
package pl.syncraft.presto.repository.jooq.generated;


import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;

import javax.annotation.Generated;


/**
 * Convenience access to all sequences in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.categories_id_seq</code>
     */
    public static final Sequence<Integer> CATEGORIES_ID_SEQ = new SequenceImpl<Integer>("categories_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>public.descriptions_id_seq</code>
     */
    public static final Sequence<Integer> DESCRIPTIONS_ID_SEQ = new SequenceImpl<Integer>("descriptions_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>public.products_id_seq</code>
     */
    public static final Sequence<Integer> PRODUCTS_ID_SEQ = new SequenceImpl<Integer>("products_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));
}