INSERT INTO customers (first_name, last_name, email)
VALUES ('John', 'Doe', 'john.doe@example.com'),
       ('Jane', 'Smith', 'jane.smith@example.com'),
       ('Alice', 'Johnson', 'alice.johnson@example.com'),
       ('Bob', 'Brown', 'bob.brown@example.com'),
       ('Carol', 'Davis', 'carol.davis@example.com'),
       ('David', 'Martinez', 'david.martinez@example.com'),
       ('Eva', 'Lewis', 'eva.lewis@example.com'),
       ('Frank', 'Clark', 'frank.clark@example.com'),
       ('Grace', 'Rodriguez', 'grace.rodriguez@example.com'),
       ('Henry', 'Lopez', 'henry.lopez@example.com');


INSERT INTO customer_details (id, date_of_birth, loyalty_points)
VALUES (1, '1985-07-15', 120),
       (2, '1990-11-30', 300),
       (3, '1982-03-22', 150),
       (4, '1995-05-19', 110),
       (5, '1993-02-11', 250),
       (6, '1979-08-25', 190),
       (7, '1988-07-09', 220),
       (8, '1991-12-13', 140),
       (9, '1980-01-16', 180),
       (10, '1987-06-05', 160);


INSERT INTO orders (customer_id, total_price)
VALUES (1, 199.99),
       (1, 89.95),
       (2, 120.00),
       (3, 299.99),
       (4, 110.50),
       (5, 450.00),
       (6, 230.00),
       (7, 75.00),
       (8, 60.00),
       (9, 90.00),
       (10, 200.00),
       (1, 150.00),
       (2, 220.00);


INSERT INTO products (name, description)
VALUES ('Laptop', 'High-performance laptop suitable for all your personal and professional needs'),
       ('Smartphone', 'Latest model smartphone with high resolution camera'),
       ('Tablet', 'Portable and versatile tablet for all your entertainment and work needs'),
       ('Headphones', 'Noise-cancelling headphones for immersive audio experiences'),
       ('Camera', 'High-definition camera to capture your precious moments');


INSERT INTO categories (name)
VALUES ('Electronics'),
       ('Home Appliances'),
       ('Personal Care'),
       ('Sporting Goods');


INSERT INTO products_categories (product_id, category_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1);

INSERT INTO order_items (quantity, order_id, product_id)
VALUES (1, 1, 1),
       (2, 2, 2),
       (1, 3, 3),
       (3, 4, 4),
       (1, 5, 5),
       (1, 6, 1),
       (2, 7, 2),
       (3, 8, 3),
       (1, 9, 4),
       (2, 10, 5),
       (1, 11, 1),
       (2, 12, 2),
       (3, 13, 3);
