# rmt_market schema


# --- !Ups

create table game_data(
    title varchar(255) not null,
    img_src varchar(255) not null,
    game_title_data_id int(4) not null,
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

create table game_title_data(
    game_title_id int(4) not null unique,
    game_title varchar(100) not null,
    game_img varchar(255) not null
) engine=innodb charset=utf8mb4;

INSERT INTO game_title_data (game_title_id, game_title, game_img)
VALUES (1, "Apex", "https://media.contentapi.ea.com/content/dam/apex-legends/common/logos/apex-dark-logo-nomargin.svg"),
(2, "Fortnite", "https://static.e-sports-press.com/wp-content/uploads/2019/10/fortnite-1200x630.jpg"),
(3, "valorant", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeZEHlkpUoUYrRXIlvZzIzrqwNqa96vu8_sY2eMEkTxw&s"),
(4, "あつ森", "https://i.pinimg.com/originals/ff/90/4a/ff904a56c7a02e9eeaf8a2175504ed17.png"),
(5, "ドラクエ10", "https://www.dqx.jp/online/assets/img/common/ogp.png"),
(6, "ポケモン剣盾", "https://www.pokemon.co.jp/ex/sword_shield/assets/og/tw.jpg"),
(7, "ポケモンSV", "https://www.pokemon.co.jp/ex/sv/assets/img/news/ja/news_231214_01.jpg"),
(8, "FF14", "https://ogre.natalie.mu/media/news/comic/2020/0812/FFXIV_logo.jpg?imwidth=750&imdensity=1"),
(9, "モンスト", "https://yt3.googleusercontent.com/XDEO2FUOO38gJXMXj8qX8coDaYoA_QwLhnofYav_fnDH8KdLVVUoDHd0XZ--Y0dqWc5yEulQ_w=s900-c-k-c0x00ffffff-no-rj"),
(10, "プロスピA", "https://i3.gamebiz.jp/images/original/183547430156272ef01217d0016.jpg"),
(11, "FGO", "https://ogre.natalie.mu/media/news/comic/2018/0729/fgo_logo.jpg?imwidth=750&imdensity=1"),
(12, "バウンティラッシュ", "https://opbr.bn-ent.net/assets/data/webp/ja/common/logo.png.webp"),
(13, "原神", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStGddWH37wq_h8tYyuu3apRVTT4uxaYQ7C1Ft0LFFc7g&s"),
(14, "ポケモンGO", "https://www.pokemongo.jp/assets/img/logo.png"),
(15, "荒野行動", "https://pics.prcm.jp/a4107a0f22ced/75691685/png/75691685_480x346.png"),
(16, "パズドラ", "https://prtimes.jp/i/25063/980/resize/d25063-980-610558-4.jpg");

# --- !Downs

drop table game_data;

drop table category;

drop table site;

drop table game_title_data;