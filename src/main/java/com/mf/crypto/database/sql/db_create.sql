CREATE TABLE coin(
    id uuid DEFAULT uuid_generate_v4(),
    symbol varchar UNIQUE NOT NULL,
    price decimal DEFAULT 0.0,
    PRIMARY KEY(id)
);
ALTER TABLE
    coin
ADD
    CONSTRAINT symbol CHECK (
           symbol = 'BTCTUSD'
        OR symbol = 'ETHTUSD'
        OR symbol = 'LTCBTC'
        OR symbol = 'BNBBTC'
        OR symbol = 'DOGEUSDT'
        OR symbol = 'SOLAUD');
