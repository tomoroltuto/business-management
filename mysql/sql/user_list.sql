
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(256) NOT NULL,
    user_name VARCHAR(256) NOT NULL
 );


INSERT INTO users (id, company_name, user_name) values (1, '○○○会社', '瀬川' );
INSERT INTO users (id, company_name, user_name) values (2, '△△△会社', '瀬川２' );
