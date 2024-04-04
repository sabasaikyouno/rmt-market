# rmt_market schema


# --- !Ups

create table game_data(
    title varchar(255) not null,
    img_src varchar(255) not null,
    game_title_id int(4) not null,
    detail varchar(10000) not null,
    price int(4) not null,
    url varchar(100) not null unique,
    category_id int(4) not null,
    site_id int(4) not null,
    created_time datetime not null,
    updated_time datetime not null
) engine=innodb charset=utf8mb4;

create table category(
    category_id int(4) not null unique,
    category varchar(100) not null unique
) engine=innodb charset=utf8mb4;

create table site(
    site_id int(4) not null unique,
    site varchar(100) not null unique
) engine=innodb charset=utf8mb4;

create table game_title(
    game_title_id int(4) not null unique,
    game_title varchar(255) not null unique
) engine=innodb charset=utf8mb4;

# --- !Downs

drop table game_data;