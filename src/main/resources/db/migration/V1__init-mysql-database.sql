drop table if exists library;
drop table if exists account;

CREATE TABLE library(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(80) NOT NULL,
                        address VARCHAR(255) NOT NULL
);

CREATE TABLE account(
                        id INTEGER AUTO_INCREMENT NOT NULL,
                        dtype VARCHAR(31),
                        password VARCHAR(36) NOT NULL,
                        account_type ENUM('MEMBER', 'LIBRARIAN') NOT NULL,
                        account_status ENUM('ACTIVE', 'CLOSED', 'CANCELED', 'BLACKLISTED', 'NONE') NOT NULL,
                        name VARCHAR(100) NOT NULL,
                        address VARCHAR(100) NOT NULL,
                        email VARCHAR(255) NOT NULL ,
                        phone VARCHAR(20) NOT NULL,
                        department  VARCHAR(20),
                        date_of_membership DATE,
                        total_books_checked_out INTEGER,
                        PRIMARY KEY (id)
);


