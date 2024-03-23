INSERT INTO "USER" (USERNAME, PASSWORD, ROLE, DETAILS)
VALUES ('user1', 'password1', 'user', 'Details for user 1'),
       ('user2', 'password2', 'user', 'Details for user 2'),
       ('admin1', 'adminpass1', 'admin', 'Details for admin 1'),
       ('user3', 'password3', 'user', 'Details for user 3'),
       ('user4', 'password4', 'user', 'Details for user 4');

INSERT INTO "WORK" (TITLE, AUTHOR_ID, DESCRIPTION, CREATE_DATE, FILE_URL)
VALUES ('Work 1', 1, 'Description for Work 1', '2024-02-25 10:00:00', 'http://example.com/work1'),
       ('Work 2', 1, 'Description for Work 2', '2024-02-26 10:00:00', 'http://example.com/work2'),
       ('Work 3', 2, 'Description for Work 3', '2024-02-27 10:00:00', 'http://example.com/work3'),
       ('Work 4', 3, 'Description for Work 4', '2024-02-28 10:00:00', 'http://example.com/work4'),
       ('Work 5', 4, 'Description for Work 5', '2024-02-29 10:00:00', 'http://example.com/work5'),
       ('Work 6', 1, 'Description for Work 6', '2024-03-01 10:00:00', 'http://example.com/work6'),
       ('Work 7', 2, 'Description for Work 7', '2024-03-02 10:00:00', 'http://example.com/work7'),
       ('Work 8', 3, 'Description for Work 8', '2024-03-03 10:00:00', 'http://example.com/work8'),
       ('Work 9', 4, 'Description for Work 9', '2024-03-04 10:00:00', 'http://example.com/work9'),
       ('Work 10', 1, 'Description for Work 10', '2024-03-05 10:00:00', 'http://example.com/work10'),
       ('Work 11', 2, 'Description for Work 11', '2024-03-06 10:00:00', 'http://example.com/work11'),
       ('Work 12', 3, 'Description for Work 12', '2024-03-07 10:00:00', 'http://example.com/work12'),
       ('Work 13', 4, 'Description for Work 13', '2024-03-08 10:00:00', 'http://example.com/work13'),
       ('Work 14', 1, 'Description for Work 14', '2024-03-09 10:00:00', 'http://example.com/work14'),
       ('Work 15', 2, 'Description for Work 15', '2024-03-10 10:00:00', 'http://example.com/work15');

INSERT INTO "WATCHLIST" (WORK_ID, USER_ID)
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

INSERT INTO "COMMENT" (WORK_ID, USER_ID, COMMENT)
VALUES (1, 2, 'Comment 1 for Work 1'),
       (2, 1, 'Comment 1 for Work 2'),
       (3, 3, 'Comment 1 for Work 3'),
       (4, 4, 'Comment 1 for Work 4'),
       (5, 5, 'Comment 1 for Work 5'),
       (6, 2, 'Comment 1 for Work 6'),
       (7, 1, 'Comment 1 for Work 7'),
       (8, 3, 'Comment 1 for Work 8'),
       (9, 4, 'Comment 1 for Work 9'),
       (10, 5, 'Comment 1 for Work 10'),
       (11, 2, 'Comment 1 for Work 11'),
       (12, 1, 'Comment 1 for Work 12'),
       (13, 3, 'Comment 1 for Work 13'),
       (14, 4, 'Comment 1 for Work 14'),
       (15, 5, 'Comment 1 for Work 15');

INSERT INTO "RATING" (WORK_ID, USER_ID, RATING)
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