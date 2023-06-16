CREATE TABLE AUTH_USER(
    email VARCHAR(40) not null,
    pass VARCHAR(12) not null,
    PRIMARY KEY (email)
);
INSERT INTO AUTH_USER ( email, pass) 
VALUES ('example@example.com', 'password');
