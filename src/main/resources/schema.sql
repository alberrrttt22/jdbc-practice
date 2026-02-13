DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors(
  id BIGINT GENERATED ALWAYS AS IDENTITY
      PRIMARY KEY,
  name TEXT,
  age INTEGER CHECK (age > 0)
);

CREATE TABLE books(
    isbn TEXT PRIMARY KEY,
    title TEXT,
    author_id BIGINT NOT NULL,
    CONSTRAINT fk_author
                  FOREIGN KEY (author_id)
                  REFERENCES authors(id)
);