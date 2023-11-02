--liquibase formatted sql
--changeset RustyCrazyPunky:insert-data

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    1,
    'Remera XL Blanca',
    'Remera XL Blanca',
    NULL
);

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    2,
    'Remera XL Negra',
    'Remera XL Negra',
    NULL
);

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    3,
    'Remera XL Blanca',
    'Remera XL Blanca',
    NULL
);

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    10,
    'Remera L Blanca',
    'Remera L Blanca',
    NULL
);

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    11,
    'Remera XXL Blanca',
    'Remera XXL Blanca',
    NULL
);

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    12,
    'Remera M Blanca',
    'Remera M Blanca',
    NULL
);

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    13,
    'Remera S Blanca',
    'Remera S Blanca',
    NULL
);

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    14,
    'Taza Magica Sublimada',
    'Taza Magica Sublimada',
    NULL
);

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    15,
    'Taza Sublimada',
    'Taza Sublimada',
    NULL
);

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    16,
    'Taza Sublimada Conica',
    'Taza Sublimada Conica',
    NULL
);

INSERT INTO PRODUCT (
    ID,
    NAME,
    DESCRIPTION,
    DELETED_AT
) VALUES (
    17,
    'Termo',
    'Termo',
    NULL
);

INSERT INTO CATEGORY (
    ID,
    NAME
) VALUES (
    1,
    'Cuadernos'
);

INSERT INTO CATEGORY (
    ID,
    NAME
) VALUES (
    2,
    'Sublimados'
);

INSERT INTO CATEGORY (
    ID,
    NAME
) VALUES (
    3,
    'Textiles'
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    1,
    1
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    1,
    3
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    2,
    1
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    2,
    3
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    3,
    1
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    3,
    2
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    3,
    1
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    10,
    1
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    10,
    3
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    11,
    1
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    11,
    3
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    12,
    1
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    12,
    3
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    13,
    1
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    13,
    3
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    14,
    2
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    15,
    2
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    16,
    2
);

INSERT INTO PRODUCT_CATEGORY (
    PRODUCT_ID,
    CATEGORY_ID
) VALUES (
    17,
    2
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    1,
    500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    1,
    1500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    2,
    500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    2,
    1500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    3,
    500,
    '2023-08-24 21:21:27'
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    3,
    1500,
    '2023-08-24 21:44:33'
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    3,
    1200,
    '2023-08-24 21:47:07'
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    3,
    1200,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    10,
    500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    10,
    1500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    11,
    500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    11,
    1500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    12,
    500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    12,
    1500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    13,
    500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    13,
    1500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    14,
    500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    14,
    1500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    15,
    500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    15,
    1500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    16,
    500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    16,
    1500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    17,
    500,
    NULL
);

INSERT INTO PRICE (
    PRODUCT_ID,
    PRICE,
    DELETED_AT
) VALUES (
    17,
    1500,
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    3,
    'link',
    'provider',
    '2023-08-24 21:52:11'
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    3,
    'link2',
    'provider',
    '2023-08-24 21:52:01'
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    3,
    'link2',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    10,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    11,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    12,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    13,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    14,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    15,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    16,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    17,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    1,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    2,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    10,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    11,
    'link',
    'provider',
    NULL
);

INSERT INTO PRODUCT_IMAGE (
    PRODUCT_ID,
    IMAGE_LINK,
    PROVIDER,
    DELETED_AT
) VALUES (
    12,
    'link',
    'provider',
    NULL
);