drop table if exists book;

CREATE TABLE `Book`(
    id INTEGER AUTO_INCREMENT NOT NULL,
    isbn VARCHAR(26) UNIQUE NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    language VARCHAR(20) NOT NULL,
    number_of_pages INTEGER NOT NULL
    PRIMARY KEY (id),
) ENGINE = InnoDB;

