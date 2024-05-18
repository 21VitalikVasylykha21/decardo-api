TRUNCATE TABLE "USER", "MODEL", "ART", "WATCHLIST", "COMMENT", "RATING";

INSERT INTO "USER" (USERNAME, PASSWORD, EMAIL, ROLE, DETAILS, AVATAR, BANNER, CONTACT)
VALUES
    ('user1', '$2a$10$egyehDXR6KTmXrbQoguqA./YV3WcmfmwOSiX5/n5Q0KObOIr7j5i2', 'user1@example.com', 'USER', 'User 1 details', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'contact1'),
    ('user2', '$2a$10$Jk/gXfQe.26U08sJ27y7U.6jGKjQ.NJ9MYclRh9o0u89vGLlyVjOy', 'user2@example.com', 'USER', 'User 2 details', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'contact2'),
    ('admin1', '$2a$10$A7eAdB3SMw.Jfj8dOyEdOu01Hz4AbDN2ilIQVChzHzjBxzhPVJZfm', 'admin@example.com', 'ADMIN', 'Admin 1 details', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'admin_contact'),
    ('user3', '$2a$10$lX5ygzF4GElJfRnHRXDPq.7dzkOF.9A7uG5tOjgV8G8LJ2PrTkd9K', 'user3@example.com', 'USER', 'User 3 details', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'contact3'),
    ('user4', '$2a$10$zL7JQJ3x5ZcFmo4oJdq.u.3v/BBcDx.4UqXDMMDXmEfxN1JXM1JGW', 'user4@example.com', 'USER', 'User 4 details', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'contact4'),
    ('user5', '$2a$10$Fj/hfMA0k33FLX8Cg0QDY.YMjvRBr92HbbK7MhGl3/QINapQ/B9s2', 'user5@example.com', 'USER', 'User 5 details', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media', 'contact5');

INSERT INTO "MODEL" (FILE, FORMAT, PREVIEW, BACKGROUND_COLOR, AMBIENT_LIGHT_INTENSITY, DIRECTIONAL_LIGHT_INTENSITY, HEMISPHERE_LIGHT_INTENSITY,
                     HEMISPHERE_LIGHT_GROUND_COLOR)
VALUES ('https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/183cbfdb-a40e-487a-a3be-ff4969fdf91e.fbx?alt=media', 'fbx', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/0abe1cc1-68ca-4763-92b6-ea58a401ac1f.jpg?alt=media', '#FFFFFF', 0.5, 0.8, 0.3, '#CCCCCC'),
       ('https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/183cbfdb-a40e-487a-a3be-ff4969fdf91e.fbx?alt=media', 'fbx', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/0abe1cc1-68ca-4763-92b6-ea58a401ac1f.jpg?alt=media', '#000000', 0.3, 0.7, 0.4, '#AAAAAA'),
       ('https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/183cbfdb-a40e-487a-a3be-ff4969fdf91e.fbx?alt=media', 'fbx', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/0abe1cc1-68ca-4763-92b6-ea58a401ac1f.jpg?alt=media', '#FFFFFF', 0.6, 0.9, 0.4, '#CCCCCC'),
       ('https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/183cbfdb-a40e-487a-a3be-ff4969fdf91e.fbx?alt=media', 'fbx', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/0abe1cc1-68ca-4763-92b6-ea58a401ac1f.jpg?alt=media', '#000000', 0.4, 0.6, 0.5, '#AAAAAA'),
       ('https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/183cbfdb-a40e-487a-a3be-ff4969fdf91e.fbx?alt=media', 'fbx', 'https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/0abe1cc1-68ca-4763-92b6-ea58a401ac1f.jpg?alt=media', '#FFFFFF', 0.7, 0.8, 0.5, '#CCCCCC');

INSERT INTO "ART" (TITLE, MODEL_ID, AUTHOR_ID, DESCRIPTION, TAG)
VALUES ('Art 1', 1, 1, 'Description for Art 1', ARRAY ['tag1', 'tag2']),
       ('Art 2', 2, 2, 'Description for Art 2', ARRAY ['tag3', 'tag4']),
       ('Art 3', 3, 3, 'Description for Art 3', ARRAY ['tag5', 'tag6']),
       ('Art 4', 4, 4, 'Description for Art 4', ARRAY ['tag7', 'tag8']),
       ('Art 5', 5, 5, 'Description for Art 5', ARRAY ['tag9', 'tag10']);

INSERT INTO "WATCHLIST" (ART_ID, USER_ID)
VALUES (1, 2),
       (2, 1),
       (3, 4),
       (4, 5),
       (5, 3);

INSERT INTO "COMMENT" (ART_ID, USER_ID, COMMENT)
VALUES (1, 2, 'Comment on Art 1 by User 2'),
       (2, 1, 'Comment on Art 2 by User 1'),
       (3, 4, 'Comment on Art 3 by User 4'),
       (4, 5, 'Comment on Art 4 by User 5'),
       (5, 3, 'Comment on Art 5 by User 3');

INSERT INTO "RATING" (ART_ID, USER_ID, RATING)
VALUES (1, 2, 4),
       (2, 1, 5),
       (3, 4, 4),
       (4, 5, 3),
       (5, 3, 5);