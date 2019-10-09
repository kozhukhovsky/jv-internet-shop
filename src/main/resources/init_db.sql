DROP DATABASE IF EXISTS jv_internet_shop;
CREATE DATABASE jv_internet_shop;
use jv_internet_shop;
CREATE TABLE item
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(255)  NOT NULL,
    price DECIMAL(6, 2) NOT NULL
);

INSERT INTO item (name, price)
VALUES ('MacBook', '2000.00');

INSERT INTO item (name, price)
VALUES ('Surface', '1800.00');

INSERT INTO item(name, price)
VALUES ('iPhone', '1000.00');

INSERT INTO item(name, price)
VALUES ('Pixel', '800.00');

INSERT INTO item(name, price)
VALUES ('iMac', '5000.00');

CREATE TABLE user
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(255),
    login    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    token    VARCHAR(255)
);

CREATE TABLE role
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_role
(
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO role(name)
VALUES ('ADMIN');

INSERT INTO role(name)
VALUES ('USER');

CREATE TABLE bucket
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE bucket_item
(
    bucket_id BIGINT,
    item_id   BIGINT,
    FOREIGN KEY (bucket_id) REFERENCES bucket (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES item (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `order`
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE order_item
(
    order_id BIGINT,
    item_id  BIGINT,
    FOREIGN KEY (order_id) REFERENCES `order` (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES item (id) ON UPDATE CASCADE ON DELETE CASCADE
);

ALTER TABLE user
    ADD COLUMN salt BINARY(16);

