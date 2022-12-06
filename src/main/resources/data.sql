  INSERT INTO users (id, company_name, user_name)
  values (1, '○○○会社', '瀬川' );

  INSERT INTO users (id, company_name, user_name)
  values (2, '△△△会社', '瀬川２' );



  INSERT INTO schedules (id, user_id, working_date, number_of_floors, place, work_content, number_of_people)
  values (1, 1, '2022-12-06', '4階', 'トイレ', '墨出し', '3人');
  INSERT INTO schedules (id, user_id, working_date, number_of_floors, place, work_content, number_of_people)
  values (2, 2, '2022-12-07', '3階', '和室', '配線', '5人');
  INSERT INTO schedules (id, user_id, working_date, number_of_floors, place, work_content, number_of_people)
  values (3, 1, '2022-12-04', '11階', '洋室', '相番', '1人');
  INSERT INTO schedules (id, user_id, working_date, number_of_floors, place, work_content, number_of_people)
  values (4, 1, '2022-12-07', '免震階', '機械室', '金具取り付け', '3人');