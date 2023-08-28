ALTER TABLE product_category
    DROP COLUMN deleted_at;
ALTER TABLE product_image
    DROP COLUMN deleted_at;

delete
from flyway_schema_history
where script like concat('V1_4__', '%');