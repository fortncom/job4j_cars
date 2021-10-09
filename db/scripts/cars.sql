create table if not exists cars(
    id serial primary key,
    mark_id int not null references marks(id),
    body_id int not null references bodies(id)
);