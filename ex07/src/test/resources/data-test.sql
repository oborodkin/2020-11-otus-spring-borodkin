--GENRES
insert into GENRES (name)
values ('Наука');
insert into GENRES (name)
values ('История');

--AUTHORS
insert into AUTHORS (full_name)
values ('Великий Учёный');
insert into AUTHORS (full_name)
values ('Великий Историк');

--BOOKS
insert into BOOKS (TITLE, GENRE_ID, AUTHOR_ID)
values ('Научная книга',
        (select id from GENRES where NAME = 'Наука'),
        (select id from AUTHORS where FULL_NAME = 'Великий Учёный'));

insert into BOOKS (TITLE, GENRE_ID, AUTHOR_ID)
values ('Книга по истории',
        (select id from GENRES where NAME = 'История'),
        (select id from AUTHORS where FULL_NAME = 'Великий Историк'));
