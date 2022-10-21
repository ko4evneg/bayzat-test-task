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
VALUES ('user1', 'pwd1', CURRENT_TIMESTAMP),
       ('user2', 'pwd2',  CURRENT_TIMESTAMP),
       ('user3', 'pwd3',  CURRENT_TIMESTAMP),
       ('admin', 'pwd4',  CURRENT_TIMESTAMP);

INSERT INTO alerts (name, currency_id, user_id, target_price, status, created_at)
VALUES ('BTC alert', 1, 2, 20139, 'NEW', CURRENT_TIMESTAMP);