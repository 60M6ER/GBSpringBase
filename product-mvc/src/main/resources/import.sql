DROP TABLE IF EXISTS products cascade ;
CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), price DOUBLE PRECISION, PRIMARY KEY (id));
INSERT INTO products (title, price) VALUES ('Product1', 4.35), ('Product2', 5.55);