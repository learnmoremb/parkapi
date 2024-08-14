-- liquibase formatted sql

-- changeset Shelton:2024011700001-01
-- author: Shelton
CREATE TABLE exchange_rate
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    amount_id      VARCHAR(255),
    CONSTRAINT pk_exchange_rate PRIMARY KEY (id)
);