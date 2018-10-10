INSERT INTO user(username,email,password,enabled)
VALUES ('user', 'user@example.com', '$2a$04$8aF1INkC96FBghOBoQwNpe8iW507SbIjJfYEo59ghVP0AJ8nIn5cy', true);
INSERT INTO user(username,email,password,enabled)
VALUES ('admin', 'admin@example.com', '$2a$04$EAEaOAlxpTgUj.DHHOQ1k.N3XpK0Chjm/0.tcY.S4FGwy.EX.VDl2', true);
INSERT INTO user_role (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_role (username, role)
VALUES ('admin', 'ROLE_USER');
INSERT INTO user_role (username, role)
VALUES ('user', 'ROLE_USER');