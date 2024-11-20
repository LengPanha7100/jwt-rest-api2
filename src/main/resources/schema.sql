-- CREATE DATABASE jwt_rest_api2 TEMPLATE template0;

CREATE TABLE users
(
    user_id   SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email     VARCHAR(100) NOT NULL,
    password  VARCHAR(100) NOT NULL
);

CREATE TABLE categories
(
    category_id SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    user_id     INT          NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE expenses
(
    expense_id  SERIAL PRIMARY KEY,
    amount      FLOAT        NOT NULL,
    description VARCHAR(200) NOT NULL,
    date        TIMESTAMP    NOT NULL,
    user_id     INT          NOT NULL,
    category_id INT          NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories (category_id)
);
SELECT * FROM categories;


SELECT * FROM categories WHERE category_id = 1;

INSERT INTO categories(name, description,user_id)
VALUES ('coca' , 'coca',4)
returning *;

UPDATE categories
SET name = 'koko1' , description = 'koko1' , user_id =4
WHERE category_id = 1
returning *;

DELETE FROM categories WHERE category_id =1 ;


SELECT * FROM expenses
WHERE user_id = 4
ORDER BY expense_id ASC
LIMIT 1
    OFFSET 2;

SELECT * FROM expenses WHERE expense_id = 1;

INSERT INTO expenses(amount, description, date, user_id, category_id)
VALUES (12,'kokko','2024-05-20 15:06:31.000000',4,9)
returning *;

SELECT * FROM users WHERE user_id = 4;

DELETE FROM expenses WHERE  expense_id = 19;

UPDATE expenses
SET amount = 1 , description = 'koko' , date = '2023-05-20 15:06:31.000000' , user_id = 4 , category_id = 10
WHERE expense_id = 20
returning *;