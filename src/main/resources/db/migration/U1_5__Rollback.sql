DELETE
FROM product_category
WHERE product_id IN (1, 2, 3, 10, 11, 12, 13, 14, 15, 16, 17)
or category_id IN (1,2,3);
DELETE
FROM product_image
WHERE product_id IN (1, 2, 3, 10, 11, 12, 13, 14, 15, 16, 17);
DELETE
FROM price
WHERE product_id IN (1, 2, 3, 10, 11, 12, 13, 14, 15, 16, 17);
DELETE
FROM product
WHERE id IN (1, 2, 3, 10, 11, 12, 13, 14, 15, 16, 17);
DELETE
FROM category
where id in (1, 2, 3);

delete
from flyway_schema_history
where script like concat('V1_5__', '%');