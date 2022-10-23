INSERT INTO currencies (name, symbol, current_price, enabled, created_at)
VALUES ('Bitcoin', 'BTC', 19139.22, true, CURRENT_TIMESTAMP),
       ('Tether', 'USDT', 1.0, true, CURRENT_TIMESTAMP),
       ('USD Coin', 'USDC', 0.9998, true, CURRENT_TIMESTAMP),
       ('BNB', 'BNB', 271.72, true, CURRENT_TIMESTAMP),
       ('XRP', 'XRP', 0.4559, true, CURRENT_TIMESTAMP),
       ('Dogecoin', 'DOGE', 0.05867, true, CURRENT_TIMESTAMP),
       ('Polkadot', 'DOT', 6.15, true, CURRENT_TIMESTAMP),
       ('TRON', 'TRN', 0.06199, true, CURRENT_TIMESTAMP),
       ('Avalanche', 'AVAX', 15.21, true, CURRENT_TIMESTAMP);

INSERT INTO users (username, password, created_at)
VALUES ('admin', 'pwd', CURRENT_TIMESTAMP),
       ('user1', 'pwd1', CURRENT_TIMESTAMP),
       ('user2', 'pwd2', CURRENT_TIMESTAMP),
       ('user3', 'pwd3', CURRENT_TIMESTAMP);

INSERT INTO ROLES (name, created_at)
VALUES ('USER', CURRENT_TIMESTAMP),
       ('ADMIN', CURRENT_TIMESTAMP);

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (4, 1);

INSERT INTO alerts (name, currency_id, user_id, target_price, status, target_is_more, created_at)
VALUES ('BTC alert', 1, 2, 20139, 'NEW', TRUE, CURRENT_TIMESTAMP),
       ('TRON alert', 8, 2, 0.06, 'NEW', FALSE, CURRENT_TIMESTAMP),
       ('BTC alert', 1, 3, 20200, 'NEW', TRUE, CURRENT_TIMESTAMP);