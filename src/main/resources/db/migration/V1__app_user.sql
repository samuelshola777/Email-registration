CREATE TABLE IF NOT EXISTS app_user
(
    id         bigserial primary key,
    first_name           varchar(500),
    last_name           varchar(500),
    user_name           varchar(500),
    password            varchar(500),
    phone_number        varchar(20),
    email               varchar(255)
);
