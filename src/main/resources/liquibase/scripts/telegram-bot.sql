-- liquibase formatted sql

-- changeset LeonidSysoev:1
create table animal
(
    id          serial primary key,
    animal_type varchar not null,
    "name"      varchar not null,
    age         integer not null,
    breed       varchar not null
);
create table user
(
    id           serial primary key,
    "name"       varchar not null,
    phone_number varchar not null,
    e_mail       varchar not null
);
create table bot_user
(
    id              serial primary key,
    chat_id         integer   not null,
    "name"          varchar   not null,
    local_date_time timestamp not null
);
create table information
(
    id   serial primary key,
    info text not null
);
create table user
(
    id           serial primary key,
    "name"       varchar not null,
    phone_number varchar not null,
    e_mail       varchar not null
);