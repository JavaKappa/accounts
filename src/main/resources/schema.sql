DROP TABLE IF EXISTS users;

DROP SEQUENCE if EXISTS HIBERNATE_SEQUENCE;

CREATE SEQUENCE HIBERNATE_SEQUENCE;


create table users
(
    id             numeric(30, 0) not null
        constraint users_pkey primary key,
    account_budget numeric(30, 2),
    account_number numeric(20, 0)    not null
        constraint ukd4t5vk5oufhxosw60bxqut2vq
            unique,
    birthday       date           not null,
    full_name      varchar(200)   not null
);


