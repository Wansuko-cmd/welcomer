CREATE TABLE sentMessages(
    id SERIAL not null,
    user_id varchar(50) not null,
    coming_message text,
    reply text,
    created_at timestamp
);
