# --- Sample dataset

# --- !Ups

insert into author (id, state, version, last_name, first_name, middle_name) values
    ('bdd6741d-71b9-493c-9c31-38af19b9e27c', 0, 1, 'Carroll', 'Lewis', '');

insert into author (id, state, version, last_name, first_name, middle_name) values
    ('40016228-9752-4be9-8570-c10db56f04d6', 0, 1, 'Agatha', 'Christie', '');

insert into author (id, state, version, last_name, first_name, middle_name) values
    ('32d6f93f-4fe7-4374-bd07-1d726c0415e7', 0, 1, 'James', 'Chase', 'Hadley');

insert into genre (id, state, version, name) values ('f022820c-cb84-4981-9184-46b6f9a17de8', 0, 1, 'Fantasy');
insert into genre (id, state, version, name) values ('5d85ce82-3fdf-4251-82dd-ececf927935b', 0, 1, 'Detective');

insert into book (id, state, version, name, year, edition, author_id) values
    ('0dc91ccb-9607-45b6-9b8f-1896d2289c5b', 0, 1, 'And Then There Were None', 1939, '1', '40016228-9752-4be9-8570-c10db56f04d6');

insert into book (id, state, version, name, year, edition, author_id) values
    ('d9e481b4-6cec-4069-bada-10185d2f5161', 0, 1, 'Murder on the Orient Express', 1934, '1', '40016228-9752-4be9-8570-c10db56f04d6');

insert into book (id, state, version, name, year, edition, author_id) values
    ('5bd5a25c-b06f-4abe-beb2-95323d2628c2', 0, 1, 'A Coffin from Hong Kong', 1960, '1', '32d6f93f-4fe7-4374-bd07-1d726c0415e7');

insert into book_genre (book_id, genre_id) values ('0dc91ccb-9607-45b6-9b8f-1896d2289c5b', '5d85ce82-3fdf-4251-82dd-ececf927935b');

insert into book_genre (book_id, genre_id) values ('d9e481b4-6cec-4069-bada-10185d2f5161', '5d85ce82-3fdf-4251-82dd-ececf927935b');

insert into book_genre (book_id, genre_id) values ('5bd5a25c-b06f-4abe-beb2-95323d2628c2', '5d85ce82-3fdf-4251-82dd-ececf927935b');

# --- !Downs

delete from author;
delete from genre;