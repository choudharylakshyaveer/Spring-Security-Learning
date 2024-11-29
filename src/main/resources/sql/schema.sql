#To match the schema of spring security we can use these DDL statements.

START TRANSACTION;
create table if not exists users
(
    username varchar(50)  not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);

create table if not exists authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
--create unique index ix_auth_username on authorities (username, authority);
--ALTER TABLE ix_auth_username DROP PRIMARY KEY (username, authority);

--DROP INDEX  ix_auth_username on authorities;

--create unique index ix_auth_username on authorities (username, authority);
alter table authorities add unique INDEX (username, authority);
COMMIT;