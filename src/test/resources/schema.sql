DROP TABLE IF EXISTS post;
create table post
(
    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title       varchar(256) not null,
    text        varchar(256) not null,
    tags        varchar(256) array,
    likes_count integer default 0
);