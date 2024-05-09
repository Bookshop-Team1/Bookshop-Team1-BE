--create table books
--(id bigint generated by default as identity,
-- name varchar(255) not null,
-- amount numeric not null,
-- author_name varchar(255) not null,
-- primary key (id))

CREATE TABLE books (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    amount NUMERIC NOT NULL,
    author_name VARCHAR(255) NOT NULL
);
