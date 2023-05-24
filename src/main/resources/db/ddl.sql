DROP SCHEMA IF EXISTS clip0 cascade;

CREATE SCHEMA clip0;

set schema 'clip0';

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS orderproducts CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;

CREATE TABLE users (
    id TEXT PRIMARY key,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

CREATE TABLE products (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    category TEXT NOT NULL,
    price DECIMAL NOT NULL
);

CREATE TABLE orders (
	id TEXT PRIMARY KEY,
	user_id TEXT NOT NULL,
	amount DECIMAL NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE orderproducts(
	order_id TEXT NOT NULL,
	product_id TEXT NOT NULL,
	quantity INTEGER NOT NULL,
	FOREIGN KEY (order_id) REFERENCES orders(id),
	FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE reviews (
    id TEXT PRIMARY KEY,
    rating INTEGER NOT NULL,
    comment TEXT NOT NULL,
    user_id TEXT NOT NULL,
    product_id TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);