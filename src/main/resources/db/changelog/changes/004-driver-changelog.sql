-- liquibase formatted sql

-- changeset Shelton:2024010700000-1
-- author: Shelton
ALTER TABLE driver
    ADD COLUMN is_available BOOLEAN;