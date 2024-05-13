drop table if exists lending;
drop table if exists reservation;


CREATE TABLE `lending`(
    id integer AUTO_INCREMENT NOT NULL,
    start_date date default null,
    return_date date default null,
    active boolean default false,
    book_item_id integer default null,
    library_card_id  integer default null,
    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (book_item_id) REFERENCES book_item(id),
    CONSTRAINT FOREIGN KEY (library_card_id ) REFERENCES  library_card(id)
) ENGINE = InnoDB;


CREATE TABLE `reservation`(
                          id integer AUTO_INCREMENT NOT NULL,
                          creation_date date default null,
                          active boolean default false,
                          book_item_id integer default null,
                          library_card_id  integer default null,
                          PRIMARY KEY (id),
                          CONSTRAINT FOREIGN KEY (book_item_id) REFERENCES book_item(id),
                          CONSTRAINT FOREIGN KEY (library_card_id ) REFERENCES  library_card(id)
) ENGINE = InnoDB;


