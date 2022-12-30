
  INSERT INTO users (user_id, company_name, user_name) values (1, '○○○会社', '瀬川' );

  INSERT INTO users (user_id, company_name, user_name) values (2, '△△△会社', '瀬川２' );

  INSERT INTO users (user_id, company_name, user_name) values (3, 'XXX会社', '瀬川２' );

  INSERT INTO schedules (schedule_id, user_id, working_date, place, work_content, number_of_people)
  values (1, 1, '2022-12-06', '4階トイレ', '墨出し', 3);
  INSERT INTO schedules (schedule_id, user_id, working_date, place, work_content, number_of_people)
  values (2, 2, '2022-12-07', '3階和室', '配線', 5);
  INSERT INTO schedules (schedule_id, user_id, working_date, place, work_content, number_of_people)
  values (3, 2, '2022-12-10', '3階トイレ２', 'BOX取り付け', 2);
  INSERT INTO schedules (schedule_id, user_id, working_date, place, work_content, number_of_people)
  values (4, 3, '2022-12-09', '4階洋室', '配管', 4);
