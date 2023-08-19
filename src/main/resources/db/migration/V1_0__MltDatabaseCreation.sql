create table if not exists category
(
    id         int auto_increment
        primary key,
    name       varchar(100)                       not null,
    created_at datetime default CURRENT_TIMESTAMP null
);

create table if not exists product
(
    id          int auto_increment
        primary key,
    name        varchar(100)                       not null,
    description varchar(1000)                      not null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    updated_at  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table if not exists price
(
    id         int auto_increment
        primary key,
    product_id int                                not null,
    created_at datetime default CURRENT_TIMESTAMP null,
    price      double                             not null,
    constraint price_ibfk_1
        foreign key (product_id) references product (id)
);

create index product_id
    on price (product_id);

create table if not exists product_category
(
    id          int auto_increment
        primary key,
    product_id  int                                not null,
    category_id int                                not null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    constraint product_category_ibfk_1
        foreign key (product_id) references product (id),
    constraint product_category_ibfk_2
        foreign key (category_id) references category (id)
);

create index category_id
    on product_category (category_id);

create index product_id
    on product_category (product_id);

create table if not exists product_image
(
    id         int auto_increment
        primary key,
    product_id int                                not null,
    image_link varchar(250)                       not null,
    created_at datetime default CURRENT_TIMESTAMP null,
    updated_at datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    provider   varchar(100) charset utf8mb3       not null,
    constraint product_image_ibfk_1
        foreign key (product_id) references product (id)
);

create index product_id
    on product_image (product_id);

