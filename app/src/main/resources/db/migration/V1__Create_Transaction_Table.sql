CREATE TABLE "transaction" (
    id bigserial primary key not null,
    in_acct bigint references "account"(id) not null,
    out_acct bigint references "account"(id) not null,
    amount bigint not null,
    CONSTRAINT different_accounts CHECK (in_acct <> out_acct)
);