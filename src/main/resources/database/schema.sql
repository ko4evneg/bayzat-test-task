DROP TABLE IF EXISTS alerts;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS currencies;
DROP TABLE IF EXISTS roles;


CREATE TABLE currencies
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(100) UNIQUE      NOT NULL,
    symbol        VARCHAR(10) UNIQUE           NOT NULL,
    current_price DECIMAL(20, 6)          NOT NULL,
    enabled       BOOLEAN                  NOT NULl,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE roles
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100) UNIQUE      NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100) UNIQUE      NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE user_role
(
    user_id INTEGER REFERENCES users (id) NOT NULL,
    role_id INTEGER REFERENCES roles (id) NOT NULL,
    CONSTRAINT unique_cst UNIQUE (user_id, role_id)
);

CREATE TABLE alerts
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100) UNIQUE                NOT NULL,
    currency_id  INTEGER REFERENCES currencies (id) NOT NULL,
    user_id      INTEGER REFERENCES users (id)      NOT NULL,
    target_price DECIMAL(20, 10)                    NOT NULL,
    status       VARCHAR(20)                        NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE           NOT NULL
);
