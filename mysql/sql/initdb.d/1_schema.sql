  DROP TABLE IF EXISTS users, schedules;

  CREATE TABLE users (
    user_id INT NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(256) NOT NULL,
    user_name VARCHAR(256) NOT NULL,
    PRIMARY KEY(user_id)
  );

  CREATE TABLE schedules(
    schedule_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    working_date DATE NOT NULL,
    place VARCHAR(256),
    work_content VARCHAR(256) NOT NULL,
    number_of_people INT NOT NULL,
    PRIMARY KEY(schedule_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)  ON DELETE CASCADE
  );
