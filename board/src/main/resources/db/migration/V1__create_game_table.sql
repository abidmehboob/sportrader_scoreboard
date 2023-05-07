--CREATE TABLE IF NOT EXISTS scoreboard_match (
--  id INT NOT NULL AUTO_INCREMENT,
--  home_team VARCHAR(255) NOT NULL,
--  away_team VARCHAR(255) NOT NULL,
--  home_score INT NOT NULL DEFAULT 0,
--  away_score INT NOT NULL DEFAULT 0,
--  start_time TIMESTAMP NOT NULL,
--  PRIMARY KEY (id)
--);
--
--CREATE TABLE IF NOT EXISTS scoreboard_summary (
--  id INT NOT NULL AUTO_INCREMENT,
--  match_id INT NOT NULL,
--  total_score INT NOT NULL,
--  start_time TIMESTAMP NOT NULL,
--  PRIMARY KEY (id),
--  FOREIGN KEY (match_id) REFERENCES scoreboard_match(id)
--);