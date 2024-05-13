TRUNCATE TABLE COMMENT;
TRUNCATE TABLE RATING;
TRUNCATE TABLE WORK_REFERENCE;
TRUNCATE TABLE WORK;
TRUNCATE TABLE USER;

INSERT INTO USER (USERNAME, PASSWORD_HASH, ROLE, DETAILS)
VALUES ('user1', 'password1', 'user', 'Details for user 1'),
       ('user2', 'password2', 'user', 'Details for user 2'),
       ('admin1', 'adminpass1', 'admin', 'Details for admin 1'),
       ('user3', 'password3', 'user', 'Details for user 3'),
       ('user4', 'password4', 'user', 'Details for user 4');

INSERT INTO WORK (TITLE, AUTHOR_ID, DESCRIPTION, CREATE_DATE, FILE_URL)
VALUES ('Art 1', 1, 'Description for Art 1', '2024-02-25 10:00:00', 'http://example.com/work1'),
       ('Art 2', 1, 'Description for Art 2', '2024-02-26 10:00:00', 'http://example.com/work2'),
       ('Art 3', 2, 'Description for Art 3', '2024-02-27 10:00:00', 'http://example.com/work3'),
       ('Art 4', 3, 'Description for Art 4', '2024-02-28 10:00:00', 'http://example.com/work4'),
       ('Art 5', 4, 'Description for Art 5', '2024-02-29 10:00:00', 'http://example.com/work5'),
       ('Art 6', 1, 'Description for Art 6', '2024-03-01 10:00:00', 'http://example.com/work6'),
       ('Art 7', 2, 'Description for Art 7', '2024-03-02 10:00:00', 'http://example.com/work7'),
       ('Art 8', 3, 'Description for Art 8', '2024-03-03 10:00:00', 'http://example.com/work8'),
       ('Art 9', 4, 'Description for Art 9', '2024-03-04 10:00:00', 'http://example.com/work9'),
       ('Art 10', 1, 'Description for Art 10', '2024-03-05 10:00:00', 'http://example.com/work10'),
       ('Art 11', 2, 'Description for Art 11', '2024-03-06 10:00:00', 'http://example.com/work11'),
       ('Art 12', 3, 'Description for Art 12', '2024-03-07 10:00:00', 'http://example.com/work12'),
       ('Art 13', 4, 'Description for Art 13', '2024-03-08 10:00:00', 'http://example.com/work13'),
       ('Art 14', 1, 'Description for Art 14', '2024-03-09 10:00:00', 'http://example.com/work14'),
       ('Art 15', 2, 'Description for Art 15', '2024-03-10 10:00:00', 'http://example.com/work15');

INSERT INTO WORK_REFERENCE (WORK_ID, USER_ID)
VALUES (1, 2),
       (2, 3),
       (3, 4),
       (4, 5),
       (5, 1),
       (6, 3),
       (7, 4),
       (8, 5),
       (9, 1),
       (10, 2),
       (11, 3),
       (12, 4),
       (13, 5),
       (14, 1),
       (15, 2);

INSERT INTO COMMENT (WORK_ID, USER_ID, COMMENT_TEXT)
VALUES (1, 2, 'Comment 1 for Art 1'),
       (2, 1, 'Comment 1 for Art 2'),
       (3, 3, 'Comment 1 for Art 3'),
       (4, 4, 'Comment 1 for Art 4'),
       (5, 5, 'Comment 1 for Art 5'),
       (6, 2, 'Comment 1 for Art 6'),
       (7, 1, 'Comment 1 for Art 7'),
       (8, 3, 'Comment 1 for Art 8'),
       (9, 4, 'Comment 1 for Art 9'),
       (10, 5, 'Comment 1 for Art 10'),
       (11, 2, 'Comment 1 for Art 11'),
       (12, 1, 'Comment 1 for Art 12'),
       (13, 3, 'Comment 1 for Art 13'),
       (14, 4, 'Comment 1 for Art 14'),
       (15, 5, 'Comment 1 for Art 15');

INSERT INTO RATING (WORK_ID, USER_ID, RATING_VALUE)
VALUES (1, 1, 5),
       (2, 2, 4),
       (3, 3, 3),
       (4, 4, 2),
       (5, 5, 1),
       (6, 1, 5),
       (7, 2, 4),
       (8, 3, 3),
       (9, 4, 2),
       (10, 5, 1),
       (11, 1, 5),
       (12, 2, 4),
       (13, 3, 3),
       (14, 4, 2),
       (15, 5, 1);