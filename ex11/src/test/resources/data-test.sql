insert into GENRES (name)
values ('Роман');
insert into GENRES (name)
values ('Сказка');


insert into AUTHORS (full_name)
values ('Популярный Автор 1');
insert into AUTHORS (full_name)
values ('Популярный Автор 2');
insert into AUTHORS (full_name)
values ('Автор Сказки');
insert into AUTHORS (full_name)
values ('Автор Романа');


insert into BOOKS (TITLE, GENRE_ID)
values ('Книга-Сказка', (select id from GENRES where NAME = 'Сказка'));
insert into BOOKS (TITLE, GENRE_ID)
values ('Книга-Роман', (select id from GENRES where NAME = 'Роман'));
insert into BOOKS (TITLE, GENRE_ID)
values ('Книга-Много-Авторов', (select id from GENRES where NAME = 'Сказка'));


insert into BOOKS_AUTHORS (BOOK_ID, AUTHOR_ID)
values (
           (select id from BOOKS where TITLE = 'Книга-Много-Авторов'),
           (select id from AUTHORS where FULL_NAME = 'Популярный Автор 1')
       );
insert into BOOKS_AUTHORS (BOOK_ID, AUTHOR_ID)
values (
           (select id from BOOKS where TITLE = 'Книга-Много-Авторов'),
           (select id from AUTHORS where FULL_NAME = 'Популярный Автор 2')
       );
insert into BOOKS_AUTHORS (BOOK_ID, AUTHOR_ID)
values (
           (select id from BOOKS where TITLE = 'Книга-Сказка'),
           (select id from AUTHORS where FULL_NAME = 'Автор Сказки')
       );
insert into BOOKS_AUTHORS (BOOK_ID, AUTHOR_ID)
values (
           (select id from BOOKS where TITLE = 'Книга-Роман'),
           (select id from AUTHORS where FULL_NAME = 'Автор Романа')
       );


insert into BOOKS_COMMENTS (BOOK_ID, COMMENT_TEXT)
values (
           (select id from BOOKS where TITLE = 'Книга-Сказка'),
           'Комментарий к Сказке 1'
       );
insert into BOOKS_COMMENTS (BOOK_ID, COMMENT_TEXT)
values (
           (select id from BOOKS where TITLE = 'Книга-Сказка'),
           'Комментарий к Сказке 2'
       );
insert into BOOKS_COMMENTS (BOOK_ID, COMMENT_TEXT)
values (
           (select id from BOOKS where TITLE = 'Книга-Роман'),
           'Комментарий к Роману 1'
       );
insert into BOOKS_COMMENTS (BOOK_ID, COMMENT_TEXT)
values (
           (select id from BOOKS where TITLE = 'Книга-Много-Авторов'),
           'Комментарий 4'
       );