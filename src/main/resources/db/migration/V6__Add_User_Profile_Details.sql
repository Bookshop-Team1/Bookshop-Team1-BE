--ALTER TABLE users ADD COLUMN first_name varchar;
--ALTER TABLE users ADD COLUMN last_name varchar;
--ALTER TABLE users ADD COLUMN mobile_number varchar;

ALTER TABLE users
ADD first_name VARCHAR(255);

ALTER TABLE users
ADD last_name VARCHAR(255);

ALTER TABLE users
ADD mobile_number VARCHAR(20);
