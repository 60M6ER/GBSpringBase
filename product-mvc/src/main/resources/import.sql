CREATE TABLE products (id bigserial, title VARCHAR(255), price FLOAT, PRIMARY KEY (id));
INSERT INTO products (title, price) VALUES (('Product1', 4.76), ('Product2', 5.35));