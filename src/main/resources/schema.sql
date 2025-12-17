create table if not exists post
(
    id          integer primary key,
    title       varchar(256) not null,
    text        varchar(256) not null,
    likes_count integer
);

create table if not exists comment
(
    id      integer primary key,
    text    varchar(256) not null,
    post_id integer references post (id)
);

create table if not exists tag
(
    value   varchar(256) not null,
    post_id integer references post (id)
);


