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
        'Ничего не понял, но очень интересно'
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

insert into USERS (username, password, enabled)
-- password
values ( 'Oleg', '$2y$10$A28ljZfO9cGCNRCXS.h7kuQ53NyTFf9mYoyvU4o8J3MFPX0TJe5LS', true),
       ( 'Kirill', '$2y$10$A28ljZfO9cGCNRCXS.h7kuQ53NyTFf9mYoyvU4o8J3MFPX0TJe5LS', true),
       ( 'Andrey', '$2y$10$A28ljZfO9cGCNRCXS.h7kuQ53NyTFf9mYoyvU4o8J3MFPX0TJe5LS', true),
       ( 'Ivan', '$2y$10$A28ljZfO9cGCNRCXS.h7kuQ53NyTFf9mYoyvU4o8J3MFPX0TJe5LS', true);

insert into AUTHORITIES (USER_ID, AUTHORITY)
values ( (select id from USERS where USERNAME = 'Oleg'), 'ROLE_ADMIN' ),    -- полный доступ
       ( (select id from USERS where USERNAME = 'Kirill'), 'ROLE_READ_ALL'),    -- только чтение
       ( (select id from USERS where USERNAME = 'Andrey'), 'ROLE_READ_AND_WRITE_ALL'),  -- чтение, запись, без удаления
       ( (select id from USERS where USERNAME = 'Ivan'), 'ROLE_READ_AUTHORS'),   -- только чтение жанров
       ( (select id from USERS where USERNAME = 'Ivan'), 'ROLE_READ_GENRES')   -- только чтение жанров