create table order_user
(
    id         varchar(36) not null primary key,
    created_at timestamp default now() not null,
    updated_at timestamp default now() not null,
    version    integer   default 0     not null,
    deleted_at timestamp,
    email      varchar(255)            not null,
    password   varchar(255)            not null,
    first_name varchar(255)            not null,
    last_name  varchar(255)
);

INSERT INTO order_user(id, email, password, first_name, last_name)
VALUES ('54d4648c-82e6-4e2c-b16a-dacb22511e31', 'melita@melita.com', '123456', 'Summer', 'Winter');