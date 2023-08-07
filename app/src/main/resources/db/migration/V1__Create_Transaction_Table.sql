CREATE TABLE "Transaction" (
    id bigserial primary key not null,
    in_acct bigint references "Account"(id),
    out_acct bigint references "Account"(id),
    CONSTRAINT different_accounts CHECK (in_acct <> out_acct)
);