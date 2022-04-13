create table m_idea
(
    id                  serial              primary key,
    owner_id            bigint              not null,
    category            varchar(100)        not null,
    description         varchar             not null,
    image_url           varchar             not null,
    created_at          timestamp           not null    default current_timestamp,
    updated_at          timestamp           not null    default current_timestamp,
    constraint m_idea_user_owner_id_fk foreign key (owner_id) references m_user (id)
);

-- to improve performance search we should create several indexes
create unique index m_idea_user_id_category_idx on m_idea (owner_id, category);
create index m_idea_updated_at_idx on m_idea (created_at);

create table user_idea_like
(
    id                  serial              primary key,
    user_id             bigint              not null,
    idea_id             bigint              not null,
    created_at          timestamp           not null    default current_timestamp,
    unique (user_id, idea_id),
    constraint user_idea_like_user_id_fk foreign key (user_id) references m_user (id),
    constraint user_idea_like_idea_id_fk foreign key (idea_id) references m_idea (id)
);