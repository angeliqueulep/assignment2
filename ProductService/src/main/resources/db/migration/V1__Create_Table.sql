CREATE TABLE product
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    stock       INT NOT NULL,
    image_id    BIGINT NOT NULL
);

INSERT INTO product (name, description, image_id, price, stock)
VALUES ('Jacket', 'A stylish and warm jacket', 1, 99.99, 100);

INSERT INTO product (name, description, image_id, price, stock)
VALUES ('Coat', 'A classic coat for all occasions', 2, 149.99, 75);

INSERT INTO product (name, description, image_id, price, stock)
VALUES ('Hoodie', 'A comfortable and casual hoodie', 3, 49.99, 150);
