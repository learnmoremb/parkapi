-- liquibase formatted sql

-- changeset Shelton:2024011700000-01
-- author: Shelton
ALTER TABLE payment
    ADD COLUMN email VARCHAR(255);

-- changeset Shelton:2024011700000-02
-- author: Shelton
ALTER TABLE payment
    ADD CONSTRAINT uc_email UNIQUE (email);