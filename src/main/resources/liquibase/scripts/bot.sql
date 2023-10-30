-- liquibase formatted sql

-- changeset adrot:1
CREATE TABLE animal(
    id SERIAL PRIMARY KEY ,
    animal_type VARCHAR,
    name VARCHAR,
    age SMALLINT,
    breed VARCHAR,
    path_to_photo VARCHAR
);

CREATE TABLE animal_owner(
    id BIGINT PRIMARY KEY ,
    name VARCHAR,
    photo_number VARCHAR,
    e_mail VARCHAR,
    trail_period BOOLEAN,
    date DATE
);

CREATE TABLE bot_user(
    user_id BIGINT PRIMARY KEY ,
    local_date_time TIMESTAMP,
    name VARCHAR
);

CREATE TABLE pet_report(
    id SERIAL BIGINT PRIMARY KEY
    owner_Id BIGINT,
    diet TEXT,
    feelings TEXT,
    control BOOLEAN,
    date DATE
);

CREATE TABLE photo(
    id BIGINT PRIMARY KEY ,
    date OID,
    file_path VARCHAR,
    file_size BIGINT,
    media_type VARCHAR
);

CREATE TABLE photo_report(
    id INTEGER SERIAL PRIMARY KEY,
    owner_id BIGINT,
    date DATE,
    path TEXT
);

CREATE TABLE shelter(
    id INTEGER SERIAL PRIMARY KEY,
    shelter_type VARCHAR,
    shelter_name VARCHAR,
    address VARCHAR,
    information TEXT
);

CREATE TABLE users(
    chat_id BIGINT PRIMARY KEY ,
    name VARCHAR,
    local_date_time TIMESTAMP,
    animal_id BIGINT,
    telephone VARCHAR
);

CREATE TABLE volunteer(
    id INTEGER SERIAL PRIMARY KEY,
    name VARCHAR,
    phone_number VARCHAR
);