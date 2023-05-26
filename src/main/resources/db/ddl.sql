DROP SCHEMA IF EXISTS p0 CASCADE;

CREATE SCHEMA p0;

SET SCHEMA 'p0';

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS orderproducts CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;
DROP TABLE IF EXISTS carts CASCADE;
DROP TABLE IF EXISTS cartproducts CASCADE;

CREATE TABLE users (
    id TEXT PRIMARY key,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

CREATE TABLE products (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    category TEXT NOT NULL,
    price DECIMAL NOT NULL,
	description TEXT NOT NULL
);

CREATE TABLE orders (
	id TEXT PRIMARY KEY,
	user_id TEXT NOT NULL,
	amount DECIMAL NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
	FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE orderproducts(
	id TEXT primary key,
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

CREATE TABLE carts (
	id TEXT PRIMARY KEY,
	user_id TEXT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE cartproducts (
	id TEXT primary key,
	cart_id TEXT NOT NULL,
	product_id TEXT NOT NULL,
	quantity INTEGER NOT NULL,
	FOREIGN KEY (cart_id) REFERENCES carts(id),
	FOREIGN KEY (product_id) REFERENCES products(id)
);
	