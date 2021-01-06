insert into GENRES (name)
values ('Детективный роман'),
       ('Фантастичекский роман'),
       ('Программирование');


insert into AUTHORS (full_name)
values ('Б. Маклафлин'),
       ('Г. Поллайс'),
       ('Д. Уэст'),
       ('Питер Уоттс'),
       ('Артур Конан Дойл');


insert into BOOKS (TITLE, GENRE_ID)
values (
        'Объектно-ориентированный анализ и проектирование',
        (select id from GENRES where NAME = 'Программирование')
       ),
       (
        'Ложная слепота',
        (select id from GENRES where NAME = 'Фантастичекский роман')
       ),
       (
        'Собака Баскервилей',
        (select id from GENRES where NAME = 'Детективный роман')
       ),
       (
        'Head First iPhone and iPad Development',
        (select id from GENRES where NAME = 'Программирование')
       );


insert into BOOKS_AUTHORS (BOOK_ID, AUTHOR_ID)
values (
        (select id from BOOKS where TITLE = 'Объектно-ориентированный анализ и проектирование'),
        (select id from AUTHORS where FULL_NAME = 'Б. Маклафлин')
       ),
       (
        (select id from BOOKS where TITLE = 'Объектно-ориентированный анализ и проектирование'),
        (select id from AUTHORS where FULL_NAME = 'Г. Поллайс')
       ),
       (
        (select id from BOOKS where TITLE = 'Объектно-ориентированный анализ и проектирование'),
        (select id from AUTHORS where FULL_NAME = 'Д. Уэст')
       ),
       (
        (select id from BOOKS where TITLE = 'Ложная слепота'),
        (select id from AUTHORS where FULL_NAME = 'Питер Уоттс')
       ),
       (
        (select id from BOOKS where TITLE = 'Собака Баскервилей'),
        (select id from AUTHORS where FULL_NAME = 'Артур Конан Дойл')
       ),
       (
        (select id from BOOKS where TITLE = 'Head First iPhone and iPad Development'),
        (select id from AUTHORS where FULL_NAME = 'Б. Маклафлин')
       );


insert into BOOKS_COMMENTS (BOOK_ID, COMMENT_TEXT)
values (
        (select id from BOOKS where TITLE = 'Ложная слепота'),
        'Ничего не понял но очень интересно'
       ),
       (
        (select id from BOOKS where TITLE = 'Ложная слепота'),
        'Очень много сносок'
       ),
       (
        (select id from BOOKS where TITLE = 'Собака Баскервилей'),
        'Классика!'
       ),
       (
        (select id from BOOKS where TITLE = 'Объектно-ориентированный анализ и проектирование'),
        'Хорошая серия'
       ),
       (
        (select id from BOOKS where TITLE = 'Объектно-ориентированный анализ и проектирование'),
        'Надо почитать...'
       );