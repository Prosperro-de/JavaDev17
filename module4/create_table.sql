CREATE TABLE IF NOT EXISTS customers
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS customer_details
(
    id             BIGINT PRIMARY KEY,
    date_of_birth  DATE,
    loyalty_points INT,
    FOREIGN KEY (id) references customers (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id          BIGSERIAL PRIMARY KEY,
    order_date  DATE DEFAULT now(),
    total_price DECIMAL(10, 2),
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE IF NOT EXISTS products
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS categories
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS products_categories
(
    product_id  BIGINT,
    category_id BIGINT,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS order_items
(
    id         BIGSERIAL PRIMARY KEY,
    quantity   INT,
    order_id   BIGINT,
    product_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);