drop table accounts if exists;

CREATE TABLE accounts (
    acc_Id   INTEGER NOT NULL AUTO_INCREMENT,
    account_number VARCHAR(8) NOT NULL,
    currency_balance numeric(38,2) NOT NULL,
    acc_currency VARCHAR(3) NOT NULL,
    PRIMARY KEY (acc_Id)
);