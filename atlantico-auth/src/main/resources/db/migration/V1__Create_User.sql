CREATE TABLE IF NOT EXISTS user (
  email VARCHAR(50) NOT NULL PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(500),
  created_date TIMESTAMP NOT NULL,
  updated_date TIMESTAMP,
  activated BOOLEAN DEFAULT FALSE,
  activationkey VARCHAR(50) DEFAULT NULL,
  resetpasswordkey VARCHAR(50) DEFAULT NULL
);