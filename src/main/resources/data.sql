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

create table if not exists product_detail (
                                     id int primary key
    , detail text
);



INSERT INTO product (name, price) VALUES ('Product A', 100);
INSERT INTO product (name, price) VALUES ('Product B', 1500);
INSERT INTO product (name, price) VALUES ('Product C', 2000);

insert into product_detail (id, detail) values(1, '상품 a의 상세 설명');

INSERT INTO product_quantity (id, quantity) VALUES (1, 100);
INSERT INTO product_quantity (id, quantity) VALUES (2, 10);
INSERT INTO product_quantity (id, quantity) VALUES (3, 100);

insert into point (point) values (1000);
insert into point (point) values (30000);