-- liquibase formatted sql

-- changeset Marlvin:1701936427921-1
CREATE TABLE device_tokens
(
    id            VARCHAR(255) NOT NULL,
    device_tokens TEXT
);

-- changeset Marlvin:1701936427921-2
CREATE TABLE phone_number
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    number       VARCHAR(12),
    CONSTRAINT pk_phone_number PRIMARY KEY (id)
);

-- changeset Marlvin:1701936427921-3
CREATE TABLE role
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    type         VARCHAR(255),
    CONSTRAINT pk_role PRIMARY KEY (id)
);

-- changeset Marlvin:1701936427921-4
CREATE TABLE token_refresh
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    user_id      VARCHAR(255),
    token        UUID         NOT NULL,
    expiry_date  TIMESTAMP WITH TIME ZONE,
    is_valid     BOOLEAN,
    CONSTRAINT pk_token_refresh PRIMARY KEY (id)
);

-- changeset Marlvin:1701936427921-5
CREATE TABLE user_detail
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    first_name   VARCHAR(255),
    middle_names TEXT,
    last_name    VARCHAR(255),
    email        VARCHAR(255),
    CONSTRAINT pk_user_detail PRIMARY KEY (id)
);

-- changeset Marlvin:1701936427921-6
CREATE TABLE user_detail_phone_numbers
(
    user_detail_id   VARCHAR(255) NOT NULL,
    phone_numbers_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_detail_phone_numbers PRIMARY KEY (user_detail_id, phone_numbers_id)
);

-- changeset Marlvin:1701936427921-7
CREATE TABLE users
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    username     VARCHAR(255),
    password     VARCHAR(255),
    country      VARCHAR(255),
    status       VARCHAR(255),
    detail_id    VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset Marlvin:1701936427921-8
CREATE TABLE users_roles
(
    roles_id VARCHAR(255) NOT NULL,
    users_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (roles_id, users_id)
);

-- changeset Marlvin:1701936427921-9
ALTER TABLE token_refresh
    ADD CONSTRAINT uc_token_refresh_token UNIQUE (token);

-- changeset Marlvin:1701936427921-10
ALTER TABLE user_detail_phone_numbers
    ADD CONSTRAINT uc_user_detail_phone_numbers_phonenumbers UNIQUE (phone_numbers_id);

-- changeset Marlvin:1701936427921-11
ALTER TABLE users_roles
    ADD CONSTRAINT uc_users_roles_roles UNIQUE (roles_id);

-- changeset Marlvin:1701936427921-12
ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

-- changeset Marlvin:1701936427921-13
ALTER TABLE token_refresh
    ADD CONSTRAINT FK_TOKEN_REFRESH_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset Marlvin:1701936427921-14
ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_DETAIL FOREIGN KEY (detail_id) REFERENCES user_detail (id);

-- changeset Marlvin:1701936427921-15
ALTER TABLE device_tokens
    ADD CONSTRAINT fk_device_tokens_on_user FOREIGN KEY (id) REFERENCES users (id);

-- changeset Marlvin:1701936427921-16
ALTER TABLE user_detail_phone_numbers
    ADD CONSTRAINT fk_usedetphonum_on_phone_number FOREIGN KEY (phone_numbers_id) REFERENCES phone_number (id);

-- changeset Marlvin:1701936427921-17
ALTER TABLE user_detail_phone_numbers
    ADD CONSTRAINT fk_usedetphonum_on_user_detail FOREIGN KEY (user_detail_id) REFERENCES user_detail (id);

-- changeset Marlvin:1701936427921-18
ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES role (id);

-- changeset Marlvin:1701936427921-19
ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (users_id) REFERENCES users (id);

