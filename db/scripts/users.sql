create table if not exists users(
    id serial primary key,
    name text,
    email text,
    password text,
    role_id int not null references u_roles(id)
);