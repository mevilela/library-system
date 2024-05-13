drop table if exists author;
drop table if exists publisher;
drop table if exists book;
drop table if exists rack;
drop table if exists book_item;
drop table if exists author_book;


CREATE TABLE `author`(
                         id INTEGER AUTO_INCREMENT NOT NULL,
                         author_name VARCHAR(58)  NOT NULL,
                         PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE `publisher`(
                            id INTEGER AUTO_INCREMENT NOT NULL,
                            publisher_name VARCHAR(58)  NOT NULL,
                            PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE `Book`(
                       id INTEGER AUTO_INCREMENT NOT NULL,
                       isbn VARCHAR(26) UNIQUE NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       publisher_id INTEGER NOT NULL,
                       subject VARCHAR(255) NOT NULL,
                       language VARCHAR(20) NOT NULL,
                       number_of_pages INTEGER NOT NULL,
                       PRIMARY KEY (id),
                       CONSTRAINT FOREIGN KEY (publisher_id) REFERENCES  publisher(id)
) ENGINE = InnoDB;


CREATE TABLE `rack` (
                        id INTEGER AUTO_INCREMENT NOT NULL,
                        rack_number INTEGER UNIQUE NOT NULL,
                        location VARCHAR(56) NOT NULL,
                        section VARCHAR(56) NOT NULL,
                        library_id INTEGER NOT NULL,
                        PRIMARY KEY (id),
                        CONSTRAINT FOREIGN KEY (library_id) REFERENCES  library(id)

) ENGINE = InnoDB;


CREATE TABLE `book_item`(
                            id INTEGER AUTO_INCREMENT NOT NULL,
                            book_barcode VARCHAR(255) UNIQUE NOT NULL,
                            book_id INTEGER NOT NULL,
                            rack_id INTEGER NOT NULL,
                            price FLOAT NOT NULL,
                            format ENUM('HARDCOVER','PAPERBACK','AUDIOBOOK','EBOOK','NEWSPAPER','MAGAZINE','JOURNAL') NOT NULL,
                            book_status ENUM( 'AVAILABLE','RESERVED','LOANED','LOST') NOT NULL,
                            borrow_date DATE NOT NULL,
                            due_date DATE NOT NULL,
                            date_of_purchase DATE NOT NULL,
                            publication_date DATE NOT NULL,
                            PRIMARY KEY (id),
                            CONSTRAINT FOREIGN KEY (book_id) REFERENCES  book(id),
                            CONSTRAINT FOREIGN KEY (rack_id) REFERENCES  rack(id)
)ENGINE = InnoDB;

CREATE TABLE author_book (
                             author_id INT,
                             book_id INT,
                             PRIMARY KEY (author_id, book_id),
                             FOREIGN KEY (author_id) REFERENCES author(id),
                             FOREIGN KEY (book_id) REFERENCES book(id)
);


