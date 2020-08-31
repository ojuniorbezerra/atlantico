INSERT INTO user_authority (username, authority)
SELECT * FROM (SELECT 'user1', 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT username, authority FROM user_authority WHERE username = 'user1' and authority = 'ROLE_USER'
) LIMIT 1;


INSERT INTO user_authority (username, authority)
SELECT * FROM (SELECT 'user2', 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT username, authority FROM user_authority WHERE username = 'user2' and authority = 'ROLE_ADMIN'
) LIMIT 1;

INSERT INTO user_authority (username, authority)
SELECT * FROM (SELECT 'admin', 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT username, authority FROM user_authority WHERE username = 'admin' and authority = 'ROLE_USER'
) LIMIT 1;

INSERT INTO user_authority (username, authority)
SELECT * FROM (SELECT 'admin', 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT username, authority FROM user_authority WHERE username = 'admin' and authority = 'ROLE_ADMIN'
) LIMIT 1;