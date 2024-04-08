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
    category varchar(100) not null
) engine=innodb charset=utf8mb4;

INSERT INTO category (category_id, category)
VALUES (1, "アカウント"), (2, "アイテム"), (3, "代行"), (4, "その他");

create table site(
    site_id int(4) not null unique,
    site varchar(100) not null
) engine=innodb charset=utf8mb4;

INSERT INTO site (site_id, site)
VALUES (1, "RMTClub"), (2, "GameClub"), (3, "GameTrade");

create table game_title(
    game_title_id int(4) not null unique,
    game_title varchar(100) not null
) engine=innodb charset=utf8mb4;

INSERT INTO game_title (game_title_id, game_title)
VALUES (1, "Apex"), (2, "Fortnite"), (3, "valorant"), (4, "あつ森"), (5, "ドラクエ10"), (6, "ポケモン剣盾"),
(7, "ポケモンSV"), (8, "FF14"), (9, "モンスト"), (10, "プロスピA"), (11, "FGO"), (12, "バウンティラッシュ"),
(13, "原神"), (14, "ポケモンGO"), (15, "荒野行動"), (16, "パズドラ");

# --- !Downs

drop table game_data;

drop table category;

drop table site;

drop table game_title;