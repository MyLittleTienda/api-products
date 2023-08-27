INSERT INTO product (id, name, description, deleted_at)
VALUES (1, 'Remera XL Blanca', 'Remera XL Blanca', null);
INSERT INTO product (id, name, description, deleted_at)
VALUES (2, 'Remera XL Negra', 'Remera XL Negra', null);
INSERT INTO product (id, name, description, deleted_at)
VALUES (3, 'Remera XL Blanca', 'Remera XL Blanca', null);
INSERT INTO product (id, name, description, deleted_at)
VALUES (10, 'Remera L Blanca', 'Remera L Blanca', null);
INSERT INTO product (id, name, description, deleted_at)
VALUES (11, 'Remera XXL Blanca', 'Remera XXL Blanca', null);
INSERT INTO product (id, name, description, deleted_at)
VALUES (12, 'Remera M Blanca', 'Remera M Blanca', null);
INSERT INTO product (id, name, description, deleted_at)
VALUES (13, 'Remera S Blanca', 'Remera S Blanca', null);
INSERT INTO product (id, name, description, deleted_at)
VALUES (14, 'Taza Magica Sublimada', 'Taza Magica Sublimada', null);
INSERT INTO product (id, name, description, deleted_at)
VALUES (15, 'Taza Sublimada', 'Taza Sublimada', null);
INSERT INTO product (id, name, description, deleted_at)
VALUES (16, 'Taza Sublimada Conica', 'Taza Sublimada Conica', null);
INSERT INTO product (id, name, description, deleted_at)
VALUES (17, 'Termo', 'Termo', null);


INSERT INTO category (id, name)
VALUES (1, 'Cuadernos');
INSERT INTO category (id, name)
VALUES (2, 'Sublimados');
INSERT INTO category (id, name)
VALUES (3, 'Textiles');

INSERT INTO product_category (product_id, category_id)
VALUES (1, 1);
INSERT INTO product_category (product_id, category_id)
VALUES (1, 3);
INSERT INTO product_category (product_id, category_id)
VALUES (2, 1);
INSERT INTO product_category (product_id, category_id)
VALUES (2, 3);
INSERT INTO product_category (product_id, category_id)
VALUES (3, 1);
INSERT INTO product_category (product_id, category_id)
VALUES (3, 2);
INSERT INTO product_category (product_id, category_id)
VALUES (3, 1);
INSERT INTO product_category (product_id, category_id)
VALUES (10, 1);
INSERT INTO product_category (product_id, category_id)
VALUES (10, 3);
INSERT INTO product_category (product_id, category_id)
VALUES (11, 1);
INSERT INTO product_category (product_id, category_id)
VALUES (11, 3);
INSERT INTO product_category (product_id, category_id)
VALUES (12, 1);
INSERT INTO product_category (product_id, category_id)
VALUES (12, 3);
INSERT INTO product_category (product_id, category_id)
VALUES (13, 1);
INSERT INTO product_category (product_id, category_id)
VALUES (13, 3);
INSERT INTO product_category (product_id, category_id)
VALUES (14, 2);
INSERT INTO product_category (product_id, category_id)
VALUES (15, 2);
INSERT INTO product_category (product_id, category_id)
VALUES (16, 2);
INSERT INTO product_category (product_id, category_id)
VALUES (17, 2);

INSERT INTO price (product_id, price, deleted_at)
VALUES (1, 500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (1, 1500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (2, 500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (2, 1500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (3, 500, '2023-08-24 21:21:27');
INSERT INTO price (product_id, price, deleted_at)
VALUES (3, 1500, '2023-08-24 21:44:33');
INSERT INTO price (product_id, price, deleted_at)
VALUES (3, 1200, '2023-08-24 21:47:07');
INSERT INTO price (product_id, price, deleted_at)
VALUES (3, 1200, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (10, 500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (10, 1500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (11, 500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (11, 1500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (12, 500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (12, 1500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (13, 500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (13, 1500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (14, 500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (14, 1500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (15, 500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (15, 1500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (16, 500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (16, 1500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (17, 500, null);
INSERT INTO price (product_id, price, deleted_at)
VALUES (17, 1500, null);

INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (3, 'link', 'provider', '2023-08-24 21:52:11');
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (3, 'link2', 'provider', '2023-08-24 21:52:01');
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (3, 'link2', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (10, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (11, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (12, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (13, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (14, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (15, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (16, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (17, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (1, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (2, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (10, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (11, 'link', 'provider', null);
INSERT INTO product_image (product_id, image_link, provider, deleted_at)
VALUES (12, 'link', 'provider', null);
