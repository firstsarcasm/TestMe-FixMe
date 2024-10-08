CREATE TABLE if not exists user
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username varchar2 NOT NULL UNIQUE,
    email    varchar2 NOT NULL UNIQUE,
    amount   decimal  NOT NULL,
    key      varchar2 NOT NULL UNIQUE
);