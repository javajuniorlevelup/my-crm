create schema if not exists crm_schema;

create table crm_schema.companies
(
    id         bigserial
        constraint companies_pk
            primary key,
    name       varchar   not null,
    created_at timestamp not null,
    created_by bigint,
    updated_at timestamp,
    updated_by bigint
);

create table crm_schema.contacts
(
    id         bigserial
        constraint contacts_pk
            primary key,
    firstname  varchar,
    lastname   varchar,
    middlename varchar,
    phone      varchar,
    email      varchar,
    title      varchar,
    company_id bigint
        constraint contacts_companies_id_fk
            references crm_schema.companies
            on update cascade on delete cascade,
    created_at timestamp,
    created_by bigint,
    updated_at timestamp,
    updated_by bigint
);

create table crm_schema.roles
(
    id   bigserial
        constraint roles_pk
            primary key,
    name varchar not null
);

create table crm_schema.tasks
(
    id         bigserial
        constraint tasks_pk
            primary key,
    manager_id bigint,
    name       varchar,
    details    text,
    status     varchar not null,
    created_at timestamp,
    created_by bigint,
    updated_at timestamp,
    updated_by bigint
);

create table crm_schema.user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    constraint user_roles_pk
        primary key (user_id, role_id)
);

create table crm_schema.users
(
    id         bigserial
        constraint users_pk
            primary key,
    email      varchar not null,
    password   varchar not null,
    created_at timestamp,
    created_by bigint,
    updated_at timestamp,
    updated_by bigint
);

