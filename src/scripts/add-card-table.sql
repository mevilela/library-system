drop table if exists library_card;

CREATE TABLE `library_card`(
                               card_number integer AUTO_INCREMENT NOT NULL,
                               barcode varchar (36) NOT NULL,
                               issued_at date default null,
                               active BOOLEAN default null,
                               account_id integer default null,
                               library_id integer default null,
                               PRIMARY KEY (card_Number),
                               CONSTRAINT FOREIGN KEY (account_id) REFERENCES account(id),
                               CONSTRAINT FOREIGN KEY (library_id) REFERENCES  library(id)
) ENGINE = InnoDB;