drop table if exists user;
drop table if exists user_role;
drop table if exists design_properties;
CREATE TABLE user (
 id IDENTITY PRIMARY KEY,
 username VARCHAR(45) NOT NULL UNIQUE,
 email VARCHAR(45) NOT NULL UNIQUE,
 password VARCHAR(100) NOT NULL,
 enabled TINYINT NOT NULL DEFAULT 1,
 token VARCHAR(255),
 network BLOB
);
CREATE TABLE user_role (
 id IDENTITY PRIMARY KEY,
 username VARCHAR(45) NOT NULL,
 role VARCHAR(45) NOT NULL,
 FOREIGN KEY (username) REFERENCES user (username)
);
CREATE TABLE design_properties (
 id IDENTITY PRIMARY KEY,
 user_id INT NOT NULL,
 ocjena INT,
 navbar_back_colorR INT,
 navbar_back_colorG INT,
 navbar_back_colorB INT,
 navbar_text_colorR INT,
 navbar_text_colorG INT,
 navbar_text_colorB INT,
 body_back_colorR INT,
 body_back_colorG INT,
 body_back_colorB INT,
 body_text_colorR INT,
 body_text_colorG INT,
 body_text_colorB INT,
 link_colorR INT,
 link_colorG INT,
 link_colorB INT,
 well_back_colorR INT,
 well_back_colorG INT,
 well_back_colorB INT,
 body_text_size INT,
 lead_text_size INT,
 h1text_size INT,
 h2text_size INT,
 h4text_size INT,
 body_font INT,
 h_plus_lead_font INT,
 FOREIGN KEY (user_id) REFERENCES user (id)
);
