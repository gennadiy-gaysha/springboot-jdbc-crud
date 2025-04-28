--This script:
--Safely deletes old tables if they exist
--Creates two new tables
--Links them with a foreign key
--Ensures IDs are unique and not null where needed

DROP TABLE IF EXISTS "authors";
DROP TABLE IF EXISTS "books";

CREATE TABLE "authors"(
--auto-increment the ID using a sequence called authors_id_seq
--this field cannot be empty
"id" bigint  DEFAULT nextval('author_id_seq') NOT NULL,
"name" text,
"age" integer,
--set the "id" column as the Primary Key (unique identifier for each row)
CONSTRAINT "authors_pkey" PRIMARY KEY("id")
)

CREATE TABLE "books"(
"isbn" text NOT NULL,
"title" text,
--link to the author's ID
"author_id" bigint,
CONSTRAINT "books_pkey" PRIMARY KEY("isbn"),
--Foreign Key Constraint:
--author_id in the books table must match an id from the authors table
--connects each book to a real author.
--ensures referential integrity (can't insert a book with a non-existing author)
CONSTRAINT "fk_author" FOREIGN KEY(author_id)
REFERENCES authors(id)
)
