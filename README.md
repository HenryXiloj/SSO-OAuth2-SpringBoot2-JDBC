# SSO-OAuth2-SpringBoot2-JDBC

CREATE TABLE users 
  ( 
     username VARCHAR(50) NOT NULL PRIMARY KEY, 
     password VARCHAR(100) NOT NULL, 
     enabled  BOOLEAN NOT NULL 
  ); 

CREATE TABLE authorities 
  ( 
     username  VARCHAR(50) NOT NULL, 
     authority VARCHAR(50) NOT NULL, 
     CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users( 
     username) 
  ); 

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority); 


INSERT INTO users 
VALUES      ('henry', 
             '$2a$08$ykZFaf/pmTxnShaj5dqXZeWXiK3JJSJr5f20hqiVGSO35KNyQ0lgO' 
             /*123*/, 
             true); 

INSERT INTO authorities 
            (username, 
             authority) 
VALUES     ('henry', 
            'ROLE_ADMIN'); 
