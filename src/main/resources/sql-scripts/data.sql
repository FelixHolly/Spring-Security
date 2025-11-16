-- ====================
-- Users
-- ====================
INSERT INTO users (username, password)
VALUES ('user', '{noop}password'),
       ('admin', '{noop}password'),
       ('test', '{noop}password'),
       ('me', '{noop}password');

-- ====================
-- Roles (EnumType.STRING -> enum names)
-- In this design, roles are high-level profiles that CONTAIN authorities.
-- The Role enum defines which Authority values each role has.
-- ====================
INSERT INTO user_roles (user_id, role)
VALUES (1, 'USER');

INSERT INTO user_roles (user_id, role)
VALUES (2, 'ADMIN');
INSERT INTO user_roles (user_id, role)
VALUES (2, 'USER');

INSERT INTO user_roles (user_id, role)
VALUES (3, 'USER');
INSERT INTO user_roles (user_id, role)
VALUES (4, 'USER');
