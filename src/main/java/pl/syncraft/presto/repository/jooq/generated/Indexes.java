/*
 * This file is generated by jOOQ.
 */
package pl.syncraft.presto.repository.jooq.generated;


import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;
import pl.syncraft.presto.repository.jooq.generated.tables.*;

import javax.annotation.Generated;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index CATEGORIES_PK = Indexes0.CATEGORIES_PK;
    public static final Index CATEGORIES_UN_NAME = Indexes0.CATEGORIES_UN_NAME;
    public static final Index DESCRIPTIONS_PK = Indexes0.DESCRIPTIONS_PK;
    public static final Index IMAGES_PK = Indexes0.IMAGES_PK;
    public static final Index PRODUCT_CATEGORIES_PK = Indexes0.PRODUCT_CATEGORIES_PK;
    public static final Index PRODUCT_IMAGES_PK = Indexes0.PRODUCT_IMAGES_PK;
    public static final Index PRODUCTS_PK = Indexes0.PRODUCTS_PK;
    public static final Index PRODUCTS_UN_NAME = Indexes0.PRODUCTS_UN_NAME;
    public static final Index PRODUCTS_UN_SKU = Indexes0.PRODUCTS_UN_SKU;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index CATEGORIES_PK = Internal.createIndex("categories_pk", Categories.CATEGORIES, new OrderField[] { Categories.CATEGORIES.ID }, true);
        public static Index CATEGORIES_UN_NAME = Internal.createIndex("categories_un_name", Categories.CATEGORIES, new OrderField[] { Categories.CATEGORIES.NAME }, true);
        public static Index DESCRIPTIONS_PK = Internal.createIndex("descriptions_pk", Descriptions.DESCRIPTIONS, new OrderField[] { Descriptions.DESCRIPTIONS.ID }, true);
        public static Index IMAGES_PK = Internal.createIndex("images_pk", Images.IMAGES, new OrderField[] { Images.IMAGES.URL }, true);
        public static Index PRODUCT_CATEGORIES_PK = Internal.createIndex("product_categories_pk", ProductCategories.PRODUCT_CATEGORIES, new OrderField[] { ProductCategories.PRODUCT_CATEGORIES.PRODUCT_ID, ProductCategories.PRODUCT_CATEGORIES.CATEGORY_ID }, true);
        public static Index PRODUCT_IMAGES_PK = Internal.createIndex("product_images_pk", ProductImages.PRODUCT_IMAGES, new OrderField[] { ProductImages.PRODUCT_IMAGES.PRODUCT_ID, ProductImages.PRODUCT_IMAGES.IMAGE }, true);
        public static Index PRODUCTS_PK = Internal.createIndex("products_pk", Products.PRODUCTS, new OrderField[] { Products.PRODUCTS.ID }, true);
        public static Index PRODUCTS_UN_NAME = Internal.createIndex("products_un_name", Products.PRODUCTS, new OrderField[] { Products.PRODUCTS.NAME }, true);
        public static Index PRODUCTS_UN_SKU = Internal.createIndex("products_un_sku", Products.PRODUCTS, new OrderField[] { Products.PRODUCTS.SKU }, true);
    }
}
