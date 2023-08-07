CREATE TYPE Account_Type AS ENUM (
    'Asset',
    'Liability',
    'Equity',
    'Income',
    'Expense'
);

CREATE TABLE "account" (
    id bigserial primary key not null,
    type Account_Type not null,
    name char(256) not null
);

