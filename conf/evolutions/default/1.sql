# rmt_market schema


# --- !Ups

create table game_data(
    title varchar(100) not null unique,
    img_src varchar(255) not null,
    game_title varchar(255) not null,
    detail varchar(255) not null,
    price int(4) not null,
    url varchar(255) not null,
    category varchar(255) not null
) engine=innodb charset=utf8mb4;

# --- !Downs

drop table game_data;