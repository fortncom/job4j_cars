create table if not exists advertisements(
    id serial primary key,
    descriptions text,
    sold boolean,
    created timestamp,
    car_id int not null references cars(id),
    user_id int not null references users(id)
);