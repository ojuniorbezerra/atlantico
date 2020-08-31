
INSERT INTO user (username, email, password, activated)
SELECT * FROM (SELECT 'admin', 'admin@admin.com', '$2a$10$r0RFDmpneBVryx.ihHK9gu6FFJQi4nTxQUqzdSTvrPpaKZMxigqpy', true) AS tmp
WHERE NOT EXISTS (
    SELECT username FROM user WHERE username = 'admin'
) LIMIT 1;

INSERT INTO user (username, email, password, activated)
SELECT * FROM (SELECT 'user1', 'user1@user.com', '$2a$10$r0RFDmpneBVryx.ihHK9gu6FFJQi4nTxQUqzdSTvrPpaKZMxigqpy', true) AS tmp
WHERE NOT EXISTS (
    SELECT username FROM user WHERE username = 'user1'
) LIMIT 1;


INSERT INTO user (username, email, password, activated)
SELECT * FROM (SELECT 'user2', 'user2@user.com', '$2a$10$r0RFDmpneBVryx.ihHK9gu6FFJQi4nTxQUqzdSTvrPpaKZMxigqpy', true) AS tmp
WHERE NOT EXISTS (
    SELECT username FROM user WHERE username = 'user2'
) LIMIT 1;
