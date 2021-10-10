create table if not exists ads_photos(
    id serial primary key,
    advertisement_id int not null references advertisements(id),
    photo_id int references photos(id)
);