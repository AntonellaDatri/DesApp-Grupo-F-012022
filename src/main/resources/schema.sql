DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS  users (wallet_address int not null, name varchar not null, last_name varchar not null,
email varchar not null, password varchar not null, cvu varchar not null, address varchar not null);
DROP TABLE IF EXISTS intent_transactions;
CREATE TABLE  intent_transactions(id int auto_increment, cryptoactive varchar, amount double precision, quote double precision,
arg_amount double precision, userID int NOT NULL, operation varchar)
