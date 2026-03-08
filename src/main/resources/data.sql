-- Seed data: loaded automatically by Spring on startup.
-- This gives us realistic test data without having to add games manually via the API.
-- Spring finds this file in src/main/resources and runs it against the database.

INSERT INTO games (title, genre, platform, release_year, rating) VALUES ('The Legend of Zelda: Breath of the Wild', 'Action-Adventure', 'Nintendo Switch', 2017, 9.8);
INSERT INTO games (title, genre, platform, release_year, rating) VALUES ('Red Dead Redemption 2', 'Action-Adventure', 'PC', 2019, 9.7);
INSERT INTO games (title, genre, platform, release_year, rating) VALUES ('Elden Ring', 'RPG', 'PC', 2022, 9.5);
INSERT INTO games (title, genre, platform, release_year, rating) VALUES ('God of War', 'Action-Adventure', 'PS5', 2018, 9.4);
INSERT INTO games (title, genre, platform, release_year, rating) VALUES ('Hades', 'Roguelite', 'PC', 2020, 9.3);
INSERT INTO games (title, genre, platform, release_year, rating) VALUES ('Disco Elysium', 'RPG', 'PC', 2019, 9.2);
INSERT INTO games (title, genre, platform, release_year, rating) VALUES ('Hollow Knight', 'Metroidvania', 'PC', 2017, 9.1);
INSERT INTO games (title, genre, platform, release_year, rating) VALUES ('Celeste', 'Platformer', 'PC', 2018, 9.0);
