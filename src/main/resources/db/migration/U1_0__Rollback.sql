drop table if exists product_image;
drop table if exists price;
drop table if exists product_category;
drop table if exists product;
drop table if exists category;


delete
from flyway_schema_history
where script like concat('V1_0__', '%');