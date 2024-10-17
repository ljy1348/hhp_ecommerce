CREATE TABLE IF NOT EXISTS product (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255),
    price int
    );

CREATE TABLE IF NOT EXISTS product_quantity (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
quantity int
    );

create table if not exists point (
    id int AUTO_INCREMENT primary key
, point int
);



INSERT INTO product (name, price) VALUES ('Product A', 1000);
INSERT INTO product (name, price) VALUES ('Product B', 1500);
INSERT INTO product (name, price) VALUES ('Product C', 2000);

INSERT INTO product_quantity (id, quantity) VALUES (1, 10);
INSERT INTO product_quantity (id, quantity) VALUES (2, 10);
INSERT INTO product_quantity (id, quantity) VALUES (3, 10);

insert into point (point) values (10000);
insert into point (point) values (30000);