-- noinspection SqlNoDataSourceInspection
CREATE TABLE IF NOT EXISTS levels (
    id SERIAL PRIMARY KEY,
    Lvl_name VARCHAR(255),
    Lvl_difficulty VARCHAR(20),
    Lvl_number INT
    );

CREATE TABLE IF NOT EXISTS players (
    id SERIAL PRIMARY KEY,
    Pl_name VARCHAR(10),
    Pl_lifes INT,
    levels_id INT,
    FOREIGN KEY (levels_id) REFERENCES levels(id)
    );