DATABASE query:
CREATE TABLE Players (
  player_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE Games (
  game_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  player1_id INT NOT NULL,
  player2_id INT NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  winner_id INT,
  loser_id INT,
  FOREIGN KEY (player1_id) REFERENCES Players(player_id)
);
