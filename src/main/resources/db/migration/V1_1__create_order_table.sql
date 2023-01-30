create table order_detail
(
    id                   varchar(36)             not null primary key,
    created_at           timestamp default now() not null,
    updated_at           timestamp default now() not null,
    version              integer   default 0     not null,
    deleted_at           timestamp,
    order_id             int                     not null,
    identity_number      varchar(255)            not null,
    customer_name        varchar(255)            not null,
    customer_last_name   varchar(255)            not null,
    phone_number         varchar(255),
    installation_address varchar(255)            not null,
    installation_date    date                    not null
);

create table order_product_info
(
    id              varchar(36)             not null primary key,
    created_at      timestamp default now() not null,
    updated_at      timestamp default now() not null,
    version         integer   default 0     not null,
    deleted_at      timestamp,
    order_id        int                     not null,
    product_type    varchar(255)            not null,
    package_type    varchar(255)            not null,
    order_detail_id varchar(36) references order_detail
);