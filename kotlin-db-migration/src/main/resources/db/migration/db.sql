CREATE TABLE kotlin_user(
  id SERIAL PRIMARY KEY,
  login VARCHAR(25) NOT NULL,
  password VARCHAR(35) NOT NULL
);

CREATE TABLE user_profile(
  user_id INT REFERENCES kotlin_user(id),
  user_profile_type_id NUMERIC(1) NOT NULL
);
-- Add constraint on columns

CREATE TABLE market(
  id SERIAL PRIMARY KEY,
  name TEXT,
  description TEXT
);