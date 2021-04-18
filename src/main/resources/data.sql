 CREATE TABLE IF NOT EXISTS task (
  recipient_email VARCHAR PRIMARY KEY,
  recipient_name VARCHAR NOT NULL);
  
INSERT INTO recipient (recipient_email, recipient_name) VALUES
  ('mohd@gmail.com', 'Mohd'),
  ('harix@gmail.com', 'Harix'),
  ('afiq@gmail.com', 'Afiq');
  
