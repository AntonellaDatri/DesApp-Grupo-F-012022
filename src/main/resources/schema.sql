DROP TABLE IF EXISTS users ;
DROP TABLE IF EXISTS transfer;
DROP TABLE IF EXISTS Intention_operate ;

CREATE TABLE IF NOT EXISTS  users ( id INT NOT NULL AUTO_INCREMENT,
                                    wallet_address int not null,
                                    name varchar not null,
                                    last_name varchar not null,
                                    email varchar not null,
                                    password varchar not null,
                                    cvu varchar not null,
                                    address varchar not null);

CREATE TABLE IF NOT EXISTS  transfer ( id INT NOT NULL AUTO_INCREMENT,
                                      AMOUNT_TO_TRANSFER DOUBLE PRECISION,
                                      DATE_TIME DATETIME,
                                      STATUS VARCHAR(255));

CREATE TABLE IF NOT EXISTS  Intention_operate ( id INT NOT NULL AUTO_INCREMENT,
                                    AMOUNT DOUBLE PRECISION,
                                    ARG_AMOUNT DOUBLE PRECISION,
                                    CRYPTOACTIVE VARCHAR(255),
                                    OPERATION VARCHAR(255),
                                    QUOTE DOUBLE PRECISION,
                                    STATUS VARCHAR(255));
