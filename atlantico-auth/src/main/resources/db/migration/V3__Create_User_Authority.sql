

CREATE TABLE IF NOT EXISTS user_authority (
    email VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (email) REFERENCES user (email),
    FOREIGN KEY (authority) REFERENCES authority (name),
    UNIQUE INDEX user_authority_idx_1 (email, authority)
);
