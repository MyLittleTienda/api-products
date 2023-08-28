ALTER TABLE product
    DROP COLUMN deleted_at;

delete
from flyway_schema_history
where script like concat('V1_1__', '%');