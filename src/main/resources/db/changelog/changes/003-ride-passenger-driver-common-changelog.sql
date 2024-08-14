-- liquibase formatted sql

-- changeset Shelton:20231213000001-1
-- author: Shelton
ALTER TABLE vehicle
    RENAME COLUMN license_number TO registration_number;

-- changeset Shelton:20231213000001-2
-- author: Shelton
ALTER TABLE ride_type
    ALTER COLUMN name SET NOT NULL;

-- changeset Shelton:20231213000001-3
-- author: Shelton
ALTER TABLE payment_type
    ALTER COLUMN name SET NOT NULL;

-- changeset Shelton:20231213000001-4
-- author: Shelton
ALTER TABLE vehicle_category
    ALTER COLUMN name SET NOT NULL;

-- changeset Shelton:20231213000001-5
-- author: Shelton
ALTER TABLE vehicle
    ALTER COLUMN model SET NOT NULL;

-- changeset Shelton:20231213000001-6
-- author: Shelton
ALTER TABLE vehicle
    ALTER COLUMN make SET NOT NULL;

-- changeset Shelton:20231213000001-7
-- author: Shelton
ALTER TABLE vehicle
    ALTER COLUMN year SET NOT NULL;

-- changeset Shelton:20231213000001-8
-- author: Shelton
ALTER TABLE phone_number
    ADD CONSTRAINT number_unique UNIQUE(number);
