CREATE TABLE crime
(
    id           SERIAL PRIMARY KEY,
    external_id  VARCHAR(255),
    bou_year     INT,
    year         INT,
    month        INT,
    day          INT,
    day_of_week  VARCHAR(255),
    hour         INT,
    aisP         VARCHAR(255),
    municipality VARCHAR(255),
    neighborhood VARCHAR(255),
    nature_type  VARCHAR(255),
    nature       VARCHAR(255),
    environment  VARCHAR(255)
);
