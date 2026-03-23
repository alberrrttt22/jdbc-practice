CREATE TABLE IF NOT EXISTS authors (
      id BIGSERIAL PRIMARY KEY,
      name VARCHAR(255),
      age INTEGER
);

CREATE TABLE IF NOT EXISTS books (
    isbn VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255),
    author_id BIGINT REFERENCES authors(id)
);
