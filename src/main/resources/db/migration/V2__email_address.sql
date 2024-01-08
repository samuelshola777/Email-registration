CREATE TABLE IF NOT EXISTS email_address
(
    id                       bigserial primary key,
    content                  varchar(5000000),
    subject                  varchar(20000),
    receiver_email            varchar(50),
    message_sending_time        timestamp(6),
    app_user_id                 bigint
    CONSTRAINT              fk_app_user_id
    REFERENCES             app_user(id)
    ON DELETE              SET NULL

    );