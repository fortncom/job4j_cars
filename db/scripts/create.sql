create table if not exists bodies(
    id serial primary key,
    name text unique not null
);

create table if not exists marks(
    id serial primary key,
    name text unique  not null
);

create table if not exists models(
    id serial primary key,
    name text unique not null ,
    body_id int not null references bodies(id)
);

create table if not exists cars(
    id serial primary key,
    mark_id int not null references marks(id),
    model_id int not null references models(id)
);

create table if not exists u_roles(
    id serial primary key,
    name text unique not null
);

create table if not exists users(
    id serial primary key,
    name text,
    email text,
    password text,
    role_id int not null references u_roles(id)
);

create table if not exists photos(
    id serial primary key,
    name text unique
);


create table if not exists advertisements(
    id serial primary key,
    descriptions text,
    price int,
    sold boolean,
    created timestamp,
    car_id int not null references cars(id),
    user_id int not null references users(id)
);

create table if not exists ads_photos(
    id serial primary key,
    advertisement_id int not null references advertisements(id),
    photo_id int references photos(id)
);
