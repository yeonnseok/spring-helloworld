create table if not exists user(
    id bigint auto_increment,
    name varchar(255) not null,
    create_at datetime,
    update_at datetime,
    primary key(id)
);