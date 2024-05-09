ALTER TABLE books ADD COLUMN image_url varchar;
ALTER TABLE books ADD COLUMN thumbnail_url varchar;
ALTER TABLE books ADD COLUMN book_count bigint;
ALTER TABLE books ADD COLUMN isbn varchar;
ALTER TABLE books ADD COLUMN isbn13 varchar;
ALTER TABLE books ADD COLUMN original_publication_year integer;
ALTER TABLE books ADD COLUMN language_code varchar;
ALTER TABLE books ADD COLUMN average_rating double precision;
