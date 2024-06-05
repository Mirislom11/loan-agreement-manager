create table if not exists loan_agreement (
    id serial primary key,
    customer_name varchar(128),
    date_start timestamp not null,
    date_end timestamp not null,
    currency varchar(128) not null,
    amount bigint not null
);