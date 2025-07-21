CREATE SCHEMA cqrsdemo;
CREATE SEQUENCE cqrsdemo.user_a_seq;
CREATE TABLE cqrsdemo.user_a
(
   id    BIGINT       NOT NULL PRIMARY KEY DEFAULT nextval('cqrsdemo.user_a_seq'),
   name  VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL
);

CREATE SEQUENCE cqrsdemo.user_b_seq;
CREATE TABLE cqrsdemo.user_b
(
   id    BIGINT       NOT NULL PRIMARY KEY DEFAULT nextval('cqrsdemo.user_b_seq'),
   name  VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL
);

CREATE SEQUENCE cqrsdemo.user_c_seq;
CREATE TABLE cqrsdemo.user_c
(
   id    BIGINT       NOT NULL PRIMARY KEY DEFAULT nextval('cqrsdemo.user_c_seq'),
   name  VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL
);