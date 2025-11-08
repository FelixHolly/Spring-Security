-- ====================
-- Users
-- ====================
INSERT INTO users (username, password) VALUES
                                           ('user',  '{noop}password'),
                                           ('admin', '{noop}password'),
                                           ('me',    '{noop}password');

-- ====================
-- Roles (EnumType.STRING -> enum names)
-- ====================
-- user_id 1 -> USER
INSERT INTO user_roles (user_id, role) VALUES (1, 'USER');

-- user_id 2 -> ADMIN (+ optionally USER if you want)
INSERT INTO user_roles (user_id, role) VALUES (2, 'ADMIN');
-- INSERT INTO user_roles (user_id, role) VALUES (2, 'ROLE_USER'); -- optional

-- user_id 3 -> USER
INSERT INTO user_roles (user_id, role) VALUES (3, 'USER');

-- ====================
-- Authorities (EnumType.STRING -> enum names)
-- Authority enum values: SECURE_GET, SECURE_POST, SECURE_PUT
-- ====================
-- user_id 1 -> read-only
INSERT INTO user_authorities (user_id, authority) VALUES
    (1, 'SECURE_GET');

-- user_id 2 -> full access
INSERT INTO user_authorities (user_id, authority) VALUES
                                                      (2, 'SECURE_GET'),
                                                      (2, 'SECURE_POST'),
                                                      (2, 'SECURE_PUT');

-- user_id 3 -> read + write (example)
INSERT INTO user_authorities (user_id, authority) VALUES
                                                      (3, 'SECURE_GET'),
                                                      (3, 'SECURE_POST');
