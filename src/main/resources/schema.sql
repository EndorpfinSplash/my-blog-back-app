create table if not exists post
(
    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title       varchar(256) not null,
    text        varchar(256) not null,
    tags        text[],
    likes_count integer
);

create table if not exists comment
(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    text    varchar(256) not null,
    post_id integer references post (id)
);



