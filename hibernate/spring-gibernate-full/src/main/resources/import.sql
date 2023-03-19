DROP TABLE IF EXISTS products cascade ;
CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), price DOUBLE PRECISION, PRIMARY KEY (id));
INSERT INTO products (title, price) VALUES ('Product1', 4.35), ('Product2', 5.55);

DROP TABLE IF EXISTS consumers cascade ;
CREATE TABLE IF NOT EXISTS consumers (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO consumers (name) VALUES ('Bob'), ('Bill'), ('BillyBob');

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE IF NOT EXISTS orders (id bigserial, product_id bigserial REFERENCES products (id), consumer_id bigserial REFERENCES consumers (id), price DOUBLE PRECISION, PRIMARY KEY (id));

INSERT INTO orders (product_id, consumer_id, price) VALUES (1, 1, 4.40), (1, 2, 5.00), (2, 1, 4.25);
