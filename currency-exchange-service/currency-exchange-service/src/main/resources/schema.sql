drop table currency_exchange if exists;

CREATE TABLE currency_exchange (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    from_ VARCHAR(3) NOT NULL,
    to_ VARCHAR(3) NOT NULL,
    conversion_multiple numeric(38,2) NOT NULL,
    PRIMARY KEY (id)
);