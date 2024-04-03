# rmt_market schema


# --- !Ups

create table game_data(
    title varchar(255) not null,
    img_src varchar(255) not null,
    game_title varchar(255) not null,
    detail varchar(10000) not null,
    price int(4) not null,
    url varchar(100) not null unique,
    category varchar(255) not null,
    site varchar(255) not null,
    created_time datetime not null,
    updated_time datetime not null
) engine=innodb charset=utf8mb4;

# --- !Downs

drop table game_data;