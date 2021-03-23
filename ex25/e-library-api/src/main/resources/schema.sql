drop table if exists books_comments;
drop table if exists books_authors;
drop table if exists books;
drop table if exists authors;
drop table if exists genres;
drop table if exists authorities;
drop table if exists users;

create table genres
(
    id bigint identity,
    name varchar(255) not null
);

create table authors
(
    id bigint identity,
    full_name varchar(255) not null
);

create table books
(
    id bigint identity,
    title varchar(255) not null,
    genre_id bigint not null,
    foreign key(genre_id) references genres(id)
);

create table books_authors
(
    book_id bigint not null,
    author_id bigint not null,
    primary key (book_id, author_id),
    foreign key(book_id) references books(id), --on delete cascade,
    foreign key(author_id) references authors(id)
);

create table books_comments
(
    id bigint identity,
    book_id bigint not null,
    comment_text varchar(255) not null,
    foreign key(book_id) references books(id) --on delete cascade
);

create table users
(
    id bigint identity,
    username varchar(50) not null,
    password varchar(500) not null,
    enabled boolean not null
);
create unique index ix_users_username on users (username);

create table authorities
(
    id bigint identity,
    user_id bigint not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(user_id) references users(id)
);
create unique index ix_auth_username on authorities (user_id,authority);