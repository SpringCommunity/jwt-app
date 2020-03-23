-- DROP ALL
DROP SEQUENCE IF EXISTS aktia_user_seq;
DROP SEQUENCE IF EXISTS aktia_role_seq;
DROP SEQUENCE IF EXISTS aktia_permission_seq;

DROP TABLE IF EXISTS aktia_permission CASCADE;
DROP TABLE IF EXISTS aktia_user CASCADE;
DROP TABLE IF EXISTS aktia_role CASCADE;

-- CREATE TABLE AKTIA_USER
CREATE SEQUENCE aktia_user_seq;
CREATE TABLE aktia_user (
   id INTEGER NOT NULL DEFAULT nextval('aktia_user_seq') PRIMARY KEY,
   username VARCHAR2(50) NOT NULL,
   password text NOT NULL,
   first_name VARCHAR2(50),
   last_name VARCHAR2(50),
   enabled BOOLEAN DEFAULT true,
   created_date TIMESTAMP DEFAULT now()
);

INSERT INTO aktia_user(username, password, first_name, last_name) VALUES ('user', '$2y$12$xGykXS6w0fpGCvdsWlXW3eeXhNlMFX/xT9MPDwUzP50DTq22/tbXS', 'Mikko', 'Valtonen');
INSERT INTO aktia_user(username, password, first_name, last_name) VALUES ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'Mirja', 'Jaakkola');

-- CREATE TABLE AKTIA_ROLE

CREATE SEQUENCE aktia_role_seq;
CREATE TABLE aktia_role (
   id INTEGER NOT NULL DEFAULT nextval('aktia_role_seq') PRIMARY KEY,
   name VARCHAR2(50) NOT NULL
);

INSERT INTO aktia_role(name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- CREATE TABLE AKTIA_PERMISSION
CREATE SEQUENCE aktia_permission_seq;
CREATE TABLE aktia_permission (
   id INTEGER NOT NULL DEFAULT nextval('aktia_permission_seq') PRIMARY KEY,
   fk_user_id INTEGER,
   fk_role_id INTEGER DEFAULT 1,
   FOREIGN KEY (fk_user_id) REFERENCES aktia_user(id),
   FOREIGN KEY (fk_role_id) REFERENCES aktia_role(id)
);

INSERT INTO aktia_permission(fk_user_id, fk_role_id) VALUES (1,1), (2,1), (2,2);

-- SELECT USER WITH ROLE AND PERMISSION
SELECT u.id, u.username, u.password, r.name FROM aktia_permission p JOIN aktia_user u on p.fk_user_id = u.id JOIN aktia_role r ON r.id = p.fk_role_id;





