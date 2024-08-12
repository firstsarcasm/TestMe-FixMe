DROP TABLE IF EXISTS user;

CREATE TABLE if not exists user
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username varchar2 NOT NULL UNIQUE,
    email    varchar2 NOT NULL UNIQUE,
    amount   decimal  NOT NULL,
    key      varchar2 NOT NULL UNIQUE
);

INSERT INTO user (username, email, amount, key)
VALUES ('Django', 'dont-call-me@nothing.com', 0, 'Lrm0XfMkbkYM0LesFhA5lmb/2Ri7pvLXxnLwZg9LWG8=');