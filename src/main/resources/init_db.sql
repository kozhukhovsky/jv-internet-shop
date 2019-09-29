CREATE DATABASE jv_internet_shop;

CREATE TABLE item
(
    item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(255)  NOT NULL,
    price   DECIMAL(6, 2) NOT NULL CHECK (price >= 0)
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