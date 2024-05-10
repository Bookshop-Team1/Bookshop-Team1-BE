--ALTER TABLE books ADD COLUMN image_url varchar;
--ALTER TABLE books ADD COLUMN thumbnail_url varchar;
--ALTER TABLE books ADD COLUMN book_count bigint;
--ALTER TABLE books ADD COLUMN isbn varchar;
--ALTER TABLE books ADD COLUMN isbn13 varchar;
--ALTER TABLE books ADD COLUMN original_publication_year integer;
--ALTER TABLE books ADD COLUMN language_code varchar;
--ALTER TABLE books ADD COLUMN average_rating double precision;

ALTER TABLE books
ADD image_url VARCHAR(255);

ALTER TABLE books
ADD thumbnail_url VARCHAR(255);

ALTER TABLE books
ADD book_count BIGINT;

ALTER TABLE books
ADD isbn VARCHAR(20);

ALTER TABLE books
ADD isbn13 VARCHAR(20);

ALTER TABLE books
ADD original_publication_year INT;

ALTER TABLE books
ADD language_code VARCHAR(10);

ALTER TABLE books
ADD average_rating FLOAT;

