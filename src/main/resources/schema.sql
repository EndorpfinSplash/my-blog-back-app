create table if not exists post
(
    id          integer primary key,
    title       varchar(256) not null,
    text        varchar(256) not null,
    tags        text[],
    likes_count integer
);

create table if not exists comment
(
    id      integer primary key,
    text    varchar(256) not null,
    post_id integer references post (id)
);



