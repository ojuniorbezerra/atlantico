INSERT INTO user_authority (email, authority)
SELECT * FROM (SELECT 'user1@user.com', 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT email, authority FROM user_authority WHERE email = 'user1@user.com' and authority = 'ROLE_USER'
) LIMIT 1;


INSERT INTO user_authority (email, authority)
SELECT * FROM (SELECT 'user2@user.com', 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT email, authority FROM user_authority WHERE email = 'user2@user.com' and authority = 'ROLE_ADMIN'
) LIMIT 1;

INSERT INTO user_authority (email, authority)
SELECT * FROM (SELECT 'admin@admin.com', 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT email, authority FROM user_authority WHERE email = 'admin@admin.com' and authority = 'ROLE_ADMIN'
) LIMIT 1;