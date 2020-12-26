--GENRES
insert into GENRES (name)
values ('Детективный роман');
insert into GENRES (name)
values ('Фантастичекский роман');
insert into GENRES (name)
values ('Программирование');

--AUTHORS
insert into AUTHORS (full_name)
values ('Гради Буч');
insert into AUTHORS (full_name)
values ('Питер Уоттс');
insert into AUTHORS (full_name)
values ('Артур Конан Дойл');

--BOOKS
insert into BOOKS (TITLE, GENRE_ID, AUTHOR_ID)
values ('Объектно-ориентированный анализ и проектирование с примерами приложений',
        (select id from GENRES where NAME = 'Программирование'),
        (select id from AUTHORS where FULL_NAME = 'Гради Буч'));

insert into BOOKS (TITLE, GENRE_ID, AUTHOR_ID)
values ('Ложная слепота',
        (select id from GENRES where NAME = 'Фантастичекский роман'),
        (select id from AUTHORS where FULL_NAME = 'Питер Уоттс'));

insert into BOOKS (TITLE, GENRE_ID, AUTHOR_ID)
values ('Собака Баскервилей',
        (select id from GENRES where NAME = 'Детективный роман'),
        (select id from AUTHORS where FULL_NAME = 'Артур Конан Дойл'));
