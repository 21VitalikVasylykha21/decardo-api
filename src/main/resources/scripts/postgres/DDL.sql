CREATE TABLE IF NOT EXISTS "USER"
(
    ID       SERIAL PRIMARY KEY,
    USERNAME VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL,
    EMAIL    VARCHAR(255) NULL UNIQUE,
    ROLE     VARCHAR(5)   NOT NULL DEFAULT 'USER' CHECK (ROLE IN ('USER', 'ADMIN')),
    DETAILS  TEXT
    );

CREATE TABLE IF NOT EXISTS "WORK"
(
    ID          SERIAL PRIMARY KEY,
    TITLE       VARCHAR(255)                        NOT NULL,
    AUTHOR_ID   INT                                 NOT NULL,
    DESCRIPTION TEXT                                NOT NULL,
    CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FILE_URL    VARCHAR(255)                        NOT NULL,
    FOREIGN KEY (AUTHOR_ID) REFERENCES "USER" (ID)
    );


CREATE TABLE IF NOT EXISTS "WATCHLIST"
(
    WORK_ID INT NOT NULL,
    USER_ID INT NOT NULL,
    PRIMARY KEY (WORK_ID, USER_ID),
    FOREIGN KEY (WORK_ID) REFERENCES "WORK" (ID),
    FOREIGN KEY (USER_ID) REFERENCES "USER" (ID)
    );

CREATE TABLE IF NOT EXISTS "COMMENT"
(
    WORK_ID     INT                                 NOT NULL,
    USER_ID     INT                                 NOT NULL,
    COMMENT     TEXT                                NOT NULL,
    CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (WORK_ID, USER_ID),
    FOREIGN KEY (WORK_ID) REFERENCES "WORK" (ID),
    FOREIGN KEY (USER_ID) REFERENCES "USER" (ID)
    );

CREATE TABLE IF NOT EXISTS "RATING"
(
    WORK_ID     INT                                 NOT NULL,
    USER_ID     INT                                 NOT NULL,
    RATING      INT                                 NOT NULL,
    CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (WORK_ID, USER_ID),
    FOREIGN KEY (WORK_ID) REFERENCES "WORK" (ID),
    FOREIGN KEY (USER_ID) REFERENCES "USER" (ID)
    );

DROP TABLE IF EXISTS "COMMENT";
DROP TABLE IF EXISTS "RATING";
DROP TABLE IF EXISTS "WATCHLIST";
DROP TABLE IF EXISTS "WORK";
DROP TABLE IF EXISTS "USER";