CREATE TABLE `type_phone` (
    id INT PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE `category` (
    id INT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    description MEDIUMTEXT
);

CREATE TABLE `country` (
    id INT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    abbreviation VARCHAR(2) NOT NULL
);

CREATE TABLE `state` (
    id INT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    abbreviation VARCHAR(2) NOT NULL,
    country_id INT NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country(id)
);

CREATE TABLE `city` (
    id INT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    abbreviation VARCHAR(2) NOT NULL,
    state_id INT NOT NULL,
    FOREIGN KEY (state_id) REFERENCES state(id)
);

CREATE TABLE `status` (
    id INT PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE `user_type` (
    id INT PRIMARY KEY,
    name VARCHAR(16) NOT NULL
);

CREATE TABLE `user` (
    id INT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    password VARCHAR(32) NOT NULL,
    email VARCHAR(128) NOT NULL UNIQUE,
    is_email_verified TINYINT(1) DEFAULT 0,
    user_type_id INT NOT NULL,
    FOREIGN KEY (user_type_id) REFERENCES user_type(id)
);

CREATE TABLE `address` (
    id INT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    detail VARCHAR(255),
    postal_code VARCHAR(16) NOT NULL,
    city_id INT NOT NULL,
    state_id INT NOT NULL,
    country_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city(id),
    FOREIGN KEY (state_id) REFERENCES state(id),
    FOREIGN KEY (country_id) REFERENCES country(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE `product` (
    id INT PRIMARY KEY,
    code VARCHAR(36) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description LONGTEXT,
    price DECIMAL(11, 2) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    product_category_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (product_category_id) REFERENCES category(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE `phone` (
    id INT PRIMARY KEY,
    ddi VARCHAR(3) NOT NULL,
    ddd VARCHAR(3) NOT NULL,
    number VARCHAR(9) NOT NULL,
    type_phone_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (type_phone_id) REFERENCES type_phone(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE `order` (
    id INT PRIMARY KEY,
    delivery_fee DECIMAL(11, 2) NOT NULL DEFAULT 0.00,
    total DECIMAL(11, 2),
    public_code VARCHAR(64) NOT NULL UNIQUE,
    status_id INT NOT NULL,
    customer_id INT NOT NULL,
    address_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (status_id) REFERENCES status(id),
    FOREIGN KEY (customer_id) REFERENCES user(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE `product_order` (
    id INT PRIMARY KEY,
    quantity INT NOT NULL,
    product_id INT NOT NULL,
    order_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (order_id) REFERENCES `order`(id)
);