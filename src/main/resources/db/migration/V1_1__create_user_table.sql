create table m_user
(
    id             serial          primary key,
    name           varchar(100)    not null,
    photo_url      varchar         not null,
    login          varchar(100)    not null,
    password       varchar         not null,
    created_at     timestamp       not null    default current_timestamp
);

create unique index m_user_login_idx on m_user (login);

create table m_role (
    id          serial          primary key,
    name        varchar(30)     not null
);

create table user_roles (
    id          serial          primary key,
    user_id     bigint          not null,
    role_id     bigint          not null,
    constraint user_roles_user_id_fk foreign key (user_id) references m_user (id),
    constraint user_roles_role_id_fk foreign key (role_id) references m_role (id)
);