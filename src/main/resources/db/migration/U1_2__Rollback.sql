ALTER TABLE category
    DROP COLUMN deleted_at;

delete
from flyway_schema_history
where script like concat('V1_2__', '%');