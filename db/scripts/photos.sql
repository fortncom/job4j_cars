create table if not exists photos(
    id serial primary key,
    name text,
    path text,
    advertisement_id int not null references advertisements(id)
);