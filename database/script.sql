create table category
(
    id          int auto_increment
        primary key,
    name        varchar(50)                        null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table carousel
(
    id          int auto_increment
        primary key,
    title       varchar(255)                       null,
    image       varchar(225)                       null,
    content     text                               null,
    id_category int                                null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint fk_carousel_category_id
        foreign key (id_category) references category (id)
);

create table color
(
    id          int auto_increment
        primary key,
    name        varchar(50) charset utf8mb3        null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table role
(
    id          int auto_increment
        primary key,
    name        varchar(30)                        null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table size
(
    id          int auto_increment
        primary key,
    name        varchar(50) charset utf8mb3        null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table product
(
    id          int auto_increment
        primary key,
    name        varchar(50) charset utf8mb3        null,
    image       varchar(225)                       null,
    price       decimal(12, 3)                     null,
    description text                               null,
    quanity     int                                null,
    id_category int                                null,
    id_size     int                                null,
    id_color    int                                null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint fk_product_category_id
        foreign key (id_category) references category (id),
    constraint fk_product_id_color
        foreign key (id_color) references color (id),
    constraint fk_product_id_size
        foreign key (id_size) references size (id)
);

create table status
(
    id          int auto_increment
        primary key,
    name        varchar(50)                        null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table tag
(
    id          int auto_increment
        primary key,
    name        varchar(30)                        null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table user
(
    id          int auto_increment
        primary key,
    username    varchar(30)                        null,
    password    varchar(255)                       null,
    email       varchar(30)                        null,
    id_role     int                                null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint fk_user_id_role
        foreign key (id_role) references role (id)
);

create table blog
(
    id          int auto_increment
        primary key,
    title       varchar(255)                       null,
    image       varchar(225)                       null,
    content     text                               null,
    id_user     int                                null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint fk_blog_id_user
        foreign key (id_user) references user (id)
);

create table blog_tag
(
    id_blog     int                                not null,
    id_tag      int                                not null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    primary key (id_blog, id_tag),
    constraint fk_blog_tag_id_blog
        foreign key (id_blog) references blog (id),
    constraint fk_blog_tag_id_tag
        foreign key (id_tag) references tag (id)
);

create table cart
(
    id          int auto_increment
        primary key,
    id_product  int                                null,
    quanity     int                                null,
    id_user     int                                null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint fk_cart_id_product
        foreign key (id_product) references product (id),
    constraint fk_cart_id_user
        foreign key (id_user) references user (id)
);

create table comment
(
    id          int auto_increment
        primary key,
    name        varchar(50) charset utf8mb3        null,
    email       varchar(225)                       null,
    content     text                               null,
    id_blog     int                                null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint fk_comment_id_blog
        foreign key (id_blog) references blog (id)
);

create table orders
(
    id          int auto_increment
        primary key,
    id_user     int                                null,
    id_status   int                                null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint fk_orders_id_status
        foreign key (id_status) references status (id),
    constraint fk_orders_id_user
        foreign key (id_user) references user (id)
);

create table product_order
(
    id_product  int                                not null,
    id_order    int                                not null,
    quanity     int                                null,
    price       decimal(12, 3)                     null,
    create_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    primary key (id_order, id_product),
    constraint fk_product_order_id_order
        foreign key (id_order) references orders (id),
    constraint fk_product_order_id_product
        foreign key (id_product) references product (id)
);


