-- liquibase formatted sql

-- changeset Marlvin:1702464870703-3
CREATE TABLE address
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    street       TEXT,
    suburb       TEXT,
    country      VARCHAR(255),
    CONSTRAINT pk_address PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-4
CREATE TABLE amount
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    value        DECIMAL,
    currency     VARCHAR(255),
    CONSTRAINT pk_amount PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-5
CREATE TABLE driver
(
    id                 VARCHAR(255) NOT NULL,
    date_created       TIMESTAMP WITH TIME ZONE,
    last_updated       TIMESTAMP WITH TIME ZONE,
    user_id            VARCHAR(255),
    address_id         VARCHAR(255),
    status             VARCHAR(255),
    status_description TEXT,
    CONSTRAINT pk_driver PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-6
CREATE TABLE driver_licenses
(
    driver_id   VARCHAR(255) NOT NULL,
    licenses_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_driver_licenses PRIMARY KEY (driver_id, licenses_id)
);

-- changeset Marlvin:1702464870703-7
CREATE TABLE driver_vehicles
(
    driver_id   VARCHAR(255) NOT NULL,
    vehicles_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_driver_vehicles PRIMARY KEY (driver_id, vehicles_id)
);

-- changeset Marlvin:1702464870703-8
CREATE TABLE drivers_license
(
    id             VARCHAR(255) NOT NULL,
    date_created   TIMESTAMP WITH TIME ZONE,
    last_updated   TIMESTAMP WITH TIME ZONE,
    license_number TEXT         NOT NULL,
    date_obtained  date         NOT NULL,
    expiry_date    TIMESTAMP WITH TIME ZONE,
    country        VARCHAR(255) NOT NULL,
    CONSTRAINT pk_drivers_license PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-9
CREATE TABLE drivers_license_images
(
    drivers_license_id VARCHAR(255) NOT NULL,
    images_id          VARCHAR(255) NOT NULL,
    CONSTRAINT pk_drivers_license_images PRIMARY KEY (drivers_license_id, images_id)
);

-- changeset Marlvin:1702464870703-10
CREATE TABLE emergency_contact
(
    id                VARCHAR(255) NOT NULL,
    date_created      TIMESTAMP WITH TIME ZONE,
    last_updated      TIMESTAMP WITH TIME ZONE,
    user_id           VARCHAR(255),
    contact_id        VARCHAR(255),
    relationship      VARCHAR(255),
    other_description TEXT,
    status            VARCHAR(255),
    CONSTRAINT pk_emergency_contact PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-11
CREATE TABLE insurance
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    name         TEXT,
    type         VARCHAR(255),
    expiry_date  TIMESTAMP WITH TIME ZONE,
    vehicle_id   VARCHAR(255),
    CONSTRAINT pk_insurance PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-12
CREATE TABLE media
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    path         TEXT,
    content_type VARCHAR(255) NOT NULL,
    name         TEXT         NOT NULL,
    CONSTRAINT pk_media PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-13
CREATE TABLE passenger
(
    id                 VARCHAR(255) NOT NULL,
    date_created       TIMESTAMP WITH TIME ZONE,
    last_updated       TIMESTAMP WITH TIME ZONE,
    user_id            VARCHAR(255),
    status             VARCHAR(255),
    status_description TEXT,
    CONSTRAINT pk_passenger PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-14
CREATE TABLE passenger_addresses
(
    passenger_id VARCHAR(255) NOT NULL,
    addresses_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_passenger_addresses PRIMARY KEY (passenger_id, addresses_id)
);

-- changeset Marlvin:1702464870703-15
CREATE TABLE payment
(
    id                       VARCHAR(255) NOT NULL,
    date_created             TIMESTAMP WITH TIME ZONE,
    last_updated             TIMESTAMP WITH TIME ZONE,
    amount_id                VARCHAR(255),
    type_id                  VARCHAR(255),
    status                   VARCHAR(255),
    upstream_reference       TEXT,
    payload                  TEXT,
    upstream_response        TEXT,
    upstream_status_response TEXT,
    description              TEXT,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-16
CREATE TABLE payment_type
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    name         VARCHAR(255),
    description  TEXT,
    status       VARCHAR(255),
    CONSTRAINT pk_payment_type PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-17
CREATE TABLE ride
(
    id                  VARCHAR(255) NOT NULL,
    date_created        TIMESTAMP WITH TIME ZONE,
    last_updated        TIMESTAMP WITH TIME ZONE,
    ride_type_id        VARCHAR(255),
    vehicle_id          VARCHAR(255),
    driver_id           VARCHAR(255),
    pick_up_address_id  VARCHAR(255),
    drop_off_address_id VARCHAR(255),
    pick_up_time        TIMESTAMP WITH TIME ZONE,
    drop_of_time        TIMESTAMP WITH TIME ZONE,
    status              VARCHAR(255),
    payment_id          VARCHAR(255),
    CONSTRAINT pk_ride PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-18
CREATE TABLE ride_passengers
(
    ride_id       VARCHAR(255) NOT NULL,
    passengers_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_ride_passengers PRIMARY KEY (ride_id, passengers_id)
);

-- changeset Marlvin:1702464870703-19
CREATE TABLE ride_review
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    ride_id      VARCHAR(255),
    rating       DOUBLE PRECISION,
    comment      TEXT,
    CONSTRAINT pk_ride_review PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-20
CREATE TABLE ride_type
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    name         VARCHAR(255),
    description  TEXT,
    status       VARCHAR(255),
    CONSTRAINT pk_ride_type PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-21
CREATE TABLE vehicle
(
    id             VARCHAR(255) NOT NULL,
    date_created   TIMESTAMP WITH TIME ZONE,
    last_updated   TIMESTAMP WITH TIME ZONE,
    make           TEXT,
    model          TEXT,
    year           BIGINT,
    color          VARCHAR(255),
    description    TEXT,
    capacity       TEXT,
    license_number VARCHAR(255),
    category_id    VARCHAR(255),
    engine_size    DOUBLE PRECISION,
    CONSTRAINT pk_vehicle PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-22
CREATE TABLE vehicle_category
(
    id           VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITH TIME ZONE,
    last_updated TIMESTAMP WITH TIME ZONE,
    name         VARCHAR(255),
    description  TEXT,
    status       VARCHAR(255),
    CONSTRAINT pk_vehicle_category PRIMARY KEY (id)
);

-- changeset Marlvin:1702464870703-23
CREATE TABLE vehicle_images
(
    vehicle_id VARCHAR(255) NOT NULL,
    images_id  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_vehicle_images PRIMARY KEY (vehicle_id, images_id)
);

-- changeset Marlvin:1702464870703-24
ALTER TABLE user_detail
    ADD gender VARCHAR(255);

-- changeset Marlvin:1702464870703-26
ALTER TABLE driver_licenses
    ADD CONSTRAINT uc_driver_licenses_licenses UNIQUE (licenses_id);

-- changeset Marlvin:1702464870703-27
ALTER TABLE driver
    ADD CONSTRAINT uc_driver_user UNIQUE (user_id);

-- changeset Marlvin:1702464870703-28
ALTER TABLE driver_vehicles
    ADD CONSTRAINT uc_driver_vehicles_vehicles UNIQUE (vehicles_id);

-- changeset Marlvin:1702464870703-29
ALTER TABLE drivers_license_images
    ADD CONSTRAINT uc_drivers_license_images_images UNIQUE (images_id);

-- changeset Marlvin:1702464870703-30
ALTER TABLE emergency_contact
    ADD CONSTRAINT uc_emergency_contact_contact UNIQUE (contact_id);

-- changeset Marlvin:1702464870703-31
ALTER TABLE insurance
    ADD CONSTRAINT uc_insurance_vehicle UNIQUE (vehicle_id);

-- changeset Marlvin:1702464870703-32
ALTER TABLE passenger_addresses
    ADD CONSTRAINT uc_passenger_addresses_addresses UNIQUE (addresses_id);

-- changeset Marlvin:1702464870703-33
ALTER TABLE passenger
    ADD CONSTRAINT uc_passenger_user UNIQUE (user_id);

-- changeset Marlvin:1702464870703-34
ALTER TABLE payment_type
    ADD CONSTRAINT uc_payment_type_name UNIQUE (name);

-- changeset Marlvin:1702464870703-35
ALTER TABLE payment
    ADD CONSTRAINT uc_payment_upstream_reference UNIQUE (upstream_reference);

-- changeset Marlvin:1702464870703-36
ALTER TABLE ride
    ADD CONSTRAINT uc_ride_driver UNIQUE (driver_id);

-- changeset Marlvin:1702464870703-37
ALTER TABLE ride_passengers
    ADD CONSTRAINT uc_ride_passengers_passengers UNIQUE (passengers_id);

-- changeset Marlvin:1702464870703-38
ALTER TABLE ride
    ADD CONSTRAINT uc_ride_payment UNIQUE (payment_id);

-- changeset Marlvin:1702464870703-39
ALTER TABLE ride_type
    ADD CONSTRAINT uc_ride_type_name UNIQUE (name);

-- changeset Marlvin:1702464870703-40
ALTER TABLE ride
    ADD CONSTRAINT uc_ride_vehicle UNIQUE (vehicle_id);

-- changeset Marlvin:1702464870703-41
ALTER TABLE users
    ADD CONSTRAINT uc_users_detail UNIQUE (detail_id);

-- changeset Marlvin:1702464870703-42
ALTER TABLE vehicle_category
    ADD CONSTRAINT uc_vehicle_category_name UNIQUE (name);

-- changeset Marlvin:1702464870703-43
ALTER TABLE vehicle_images
    ADD CONSTRAINT uc_vehicle_images_images UNIQUE (images_id);

-- changeset Marlvin:1702464870703-44
ALTER TABLE vehicle
    ADD CONSTRAINT uc_vehicle_licensenumber UNIQUE (license_number);

-- changeset Marlvin:1702464870703-45
ALTER TABLE driver
    ADD CONSTRAINT FK_DRIVER_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);

-- changeset Marlvin:1702464870703-46
ALTER TABLE driver
    ADD CONSTRAINT FK_DRIVER_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset Marlvin:1702464870703-47
ALTER TABLE emergency_contact
    ADD CONSTRAINT FK_EMERGENCY_CONTACT_ON_CONTACT FOREIGN KEY (contact_id) REFERENCES user_detail (id);

-- changeset Marlvin:1702464870703-48
ALTER TABLE emergency_contact
    ADD CONSTRAINT FK_EMERGENCY_CONTACT_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset Marlvin:1702464870703-49
ALTER TABLE insurance
    ADD CONSTRAINT FK_INSURANCE_ON_VEHICLE FOREIGN KEY (vehicle_id) REFERENCES vehicle (id);

-- changeset Marlvin:1702464870703-50
ALTER TABLE passenger
    ADD CONSTRAINT FK_PASSENGER_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset Marlvin:1702464870703-51
ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_AMOUNT FOREIGN KEY (amount_id) REFERENCES amount (id);

-- changeset Marlvin:1702464870703-52
ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_TYPE FOREIGN KEY (type_id) REFERENCES payment_type (id);

-- changeset Marlvin:1702464870703-53
ALTER TABLE ride
    ADD CONSTRAINT FK_RIDE_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

-- changeset Marlvin:1702464870703-54
ALTER TABLE ride
    ADD CONSTRAINT FK_RIDE_ON_DROP_OFF_ADDRESS FOREIGN KEY (drop_off_address_id) REFERENCES address (id);

-- changeset Marlvin:1702464870703-55
ALTER TABLE ride
    ADD CONSTRAINT FK_RIDE_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES payment (id);

-- changeset Marlvin:1702464870703-56
ALTER TABLE ride
    ADD CONSTRAINT FK_RIDE_ON_PICK_UP_ADDRESS FOREIGN KEY (pick_up_address_id) REFERENCES address (id);

-- changeset Marlvin:1702464870703-57
ALTER TABLE ride
    ADD CONSTRAINT FK_RIDE_ON_RIDE_TYPE FOREIGN KEY (ride_type_id) REFERENCES ride_type (id);

-- changeset Marlvin:1702464870703-58
ALTER TABLE ride
    ADD CONSTRAINT FK_RIDE_ON_VEHICLE FOREIGN KEY (vehicle_id) REFERENCES vehicle (id);

-- changeset Marlvin:1702464870703-59
ALTER TABLE ride_review
    ADD CONSTRAINT FK_RIDE_REVIEW_ON_RIDE FOREIGN KEY (ride_id) REFERENCES ride (id);

-- changeset Marlvin:1702464870703-60
ALTER TABLE vehicle
    ADD CONSTRAINT FK_VEHICLE_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES vehicle_category (id);

-- changeset Marlvin:1702464870703-61
ALTER TABLE driver_licenses
    ADD CONSTRAINT fk_drilic_on_driver FOREIGN KEY (driver_id) REFERENCES driver (id);

-- changeset Marlvin:1702464870703-62
ALTER TABLE driver_licenses
    ADD CONSTRAINT fk_drilic_on_drivers_license FOREIGN KEY (licenses_id) REFERENCES drivers_license (id);

-- changeset Marlvin:1702464870703-63
ALTER TABLE drivers_license_images
    ADD CONSTRAINT fk_drilicima_on_drivers_license FOREIGN KEY (drivers_license_id) REFERENCES drivers_license (id);

-- changeset Marlvin:1702464870703-64
ALTER TABLE drivers_license_images
    ADD CONSTRAINT fk_drilicima_on_media FOREIGN KEY (images_id) REFERENCES media (id);

-- changeset Marlvin:1702464870703-65
ALTER TABLE driver_vehicles
    ADD CONSTRAINT fk_driveh_on_driver FOREIGN KEY (driver_id) REFERENCES driver (id);

-- changeset Marlvin:1702464870703-66
ALTER TABLE driver_vehicles
    ADD CONSTRAINT fk_driveh_on_vehicle FOREIGN KEY (vehicles_id) REFERENCES vehicle (id);

-- changeset Marlvin:1702464870703-67
ALTER TABLE passenger_addresses
    ADD CONSTRAINT fk_pasadd_on_address FOREIGN KEY (addresses_id) REFERENCES address (id);

-- changeset Marlvin:1702464870703-68
ALTER TABLE passenger_addresses
    ADD CONSTRAINT fk_pasadd_on_passenger FOREIGN KEY (passenger_id) REFERENCES passenger (id);

-- changeset Marlvin:1702464870703-69
ALTER TABLE ride_passengers
    ADD CONSTRAINT fk_ridpas_on_passenger FOREIGN KEY (passengers_id) REFERENCES passenger (id);

-- changeset Marlvin:1702464870703-70
ALTER TABLE ride_passengers
    ADD CONSTRAINT fk_ridpas_on_ride FOREIGN KEY (ride_id) REFERENCES ride (id);

-- changeset Marlvin:1702464870703-72
ALTER TABLE vehicle_images
    ADD CONSTRAINT fk_vehima_on_media FOREIGN KEY (images_id) REFERENCES media (id);

-- changeset Marlvin:1702464870703-73
ALTER TABLE vehicle_images
    ADD CONSTRAINT fk_vehima_on_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle (id);