# --- First database schema

# --- !Ups

create table book (
    id                      UUID not null,
    when_created            timestamp,
    when_updated            timestamp,
    state                   int,
    version                 int,
    name                    varchar(255),
    year                    numeric(4),
    edition                 varchar(10),
    author_id               UUID,
    constraint pk_book primary key (id)
);

create table author (
    id                      UUID not null,
    when_created            timestamp,
    when_updated            timestamp,
    state                   int,
    version                 int,
    last_name               varchar(255),
    first_name              varchar(255),
    middle_name             varchar(255),
    constraint pk_author primary key (id)
);

create table genre (
    id                      UUID not null,
    when_created            timestamp,
    when_updated            timestamp,
    state                   varchar(10),
    version                 int,
    name                    varchar(255),
    constraint pk_genre primary key (id)
);

create table book_genre (
    book_id                 UUID not null,
    genre_id                UUID not null
);

alter table book_genre add constraint fk_book_genre_book_1
    foreign key (book_id) references book (id)
    on delete restrict on update restrict;

alter table book_genre add constraint fk_book_genre_genre_1
    foreign key (genre_id) references genre (id)
    on delete restrict on update restrict;

alter table book add constraint fk_book_author_1
    foreign key (author_id) references author (id)
    on delete restrict on update restrict;

-- create index ix_computer_company_1 on computer (company_id);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists book;

drop table if exists author;

drop table if exists genre;

drop table if exists book_genre;

SET REFERENTIAL_INTEGRITY TRUE;

