DROP DATABASE IF EXISTS cryptotracker;
DROP ROLE IF EXISTS crypto;

CREATE DATABASE cryptotracker ENCODING UTF8;
CREATE ROLE crypto WITH LOGIN ENCRYPTED PASSWORD 'passwd';
GRANT ALL PRIVILEGES ON DATABASE cryptotracker TO crypto;
ALTER DATABASE cryptotracker OWNER TO crypto;