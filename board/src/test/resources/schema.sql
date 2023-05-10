CREATE TABLE matches (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  home_team VARCHAR(255) NOT NULL,
  away_team VARCHAR(255) NOT NULL,
  home_team_score INT NOT NULL,
  away_team_score INT NOT NULL,
  start_time TIMESTAMP NOT NULL,
  last_update_time TIMESTAMP NOT NULL
);
