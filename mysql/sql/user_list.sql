  DROP TABLE IF EXISTS users, schedules;

  CREATE TABLE users (
    user_id INT NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(256) NOT NULL,
    user_name VARCHAR(256) NOT NULL,
    PRIMARY KEY(user_id)
  );

  INSERT INTO users (user_id, company_name, user_name) values (1, '○○○会社', '瀬川' );

  INSERT INTO users (user_id, company_name, user_name) values (2, '△△△会社', '瀬川２' );

  INSERT INTO users (user_id, company_name, user_name) values (3, 'XXX会社', '瀬川２' );


  CREATE TABLE schedules
  (
      schedule_id INT NOT NULL AUTO_INCREMENT,
      user_id INT NOT NULL,
      working_date DATE NOT NULL,
      number_of_floors VARCHAR(256) NOT NULL,
      place VARCHAR(256) NOT NULL,
      work_content VARCHAR(256) NOT NULL,
      number_of_people VARCHAR(256) NOT NULL,
      PRIMARY KEY(schedule_id),
      FOREIGN KEY (user_id) REFERENCES users (user_id)  ON DELETE CASCADE
  );

    INSERT INTO schedules (schedule_id, user_id, working_date, number_of_floors, place, work_content, number_of_people)
    values (1, 1, '2022-12-06', '4階', 'トイレ', '墨出し', '3人');
    INSERT INTO schedules (schedule_id, user_id, working_date, number_of_floors, place, work_content, number_of_people)
    values (2, 2, '2022-12-07', '3階', '和室', '配線', '5人');
    INSERT INTO schedules (schedule_id, user_id, working_date, number_of_floors, place, work_content, number_of_people)
    values (3, 2, '2022-12-10', '3階', 'トイレ２', 'BOX取り付け', '2人');
    INSERT INTO schedules (schedule_id, user_id, working_date, number_of_floors, place, work_content, number_of_people)
    values (4, 3, '2022-12-09', '4階', '洋室', '配管', '4人');
