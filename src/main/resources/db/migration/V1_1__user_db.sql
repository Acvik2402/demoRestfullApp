drop schema if exists "users" cascade;
create schema users;
-- CREATE TYPE status AS ENUM (
--     'ONLINE',
--     'AWAY',
--     'OFFLINE');
create table user_table
(
    user_id       uuid not null unique,
    user_name      text not null,
    phone_number   text not null,
    email         text not null unique,
    password      text not null,
    current_status text,
    last_online_status_time timestamp not null
);