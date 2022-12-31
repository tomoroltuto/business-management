package com.example.businessmanagement2.userschedule;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DataSet(value = "userschedule/datasets/userschedules.yml")
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserScheduleApiIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @Transactional
  void ユーザーと作業予定が全件取得に成功すると200で内容を返すこと() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/userschedules"))
        .andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  @Transactional
  void ユーザーと作業予定が全件取得できること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/userschedules"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
          {
              "results": [
                  {
                      "userid": 1,
                      "companyname": "○○○会社",
                      "username": "瀬川",
                      "scheduleEntitys": [
                          {
                              "scheduleid": 1,
                              "userid": 1,
                              "workingdate": "2022-12-06",
                              "place": "4階トイレ",
                              "workcontent": "墨出し",
                              "numberofpeople": 3
                          }
                      ]
                  },
                  {
                      "userid": 2,
                      "companyname": "△△△会社",
                      "username": "瀬川2",
                      "scheduleEntitys": [
                          {
                              "scheduleid": 2,
                              "userid": 2,
                              "workingdate": "2022-12-07",
                              "place": "3階和室",
                              "workcontent": "配線",
                              "numberofpeople": 5
                          },
                          {
                              "scheduleid": 3,
                              "userid": 2,
                              "workingdate": "2022-12-10",
                              "place": "3階トイレ２",
                              "workcontent": "BOX取り付け",
                              "numberofpeople": 2
                          }
                      ]
                  },
                  {
                      "userid": 3,
                      "companyname": "xxx会社",
                      "username": "瀬川3",
                      "scheduleEntitys": [
                          {
                              "scheduleid": 4,
                              "userid": 3,
                              "workingdate": "2022-12-09",
                              "place": "4階洋室",
                              "workcontent": "配管",
                              "numberofpeople": 4
                          }
                      ]
                  }
              ]
          }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 存在するユーザーと作業予定のIDを指定したとき正常に作業予定が返されること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/userschedules/2"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
          {
              "userid": 2,
              "companyname": "△△△会社",
              "username": "瀬川2",
              "scheduleEntitys": [
                  {
                      "scheduleid": 2,
                      "userid": 2,
                      "workingdate": "2022-12-07",
                      "place": "3階和室",
                      "workcontent": "配線",
                      "numberofpeople": 5
                  },
                  {
                      "scheduleid": 3,
                      "userid": 2,
                      "workingdate": "2022-12-10",
                      "place": "3階トイレ２",
                      "workcontent": "BOX取り付け",
                      "numberofpeople": 2
                  }
              ]
          }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 存在しないユーザーと作業予定のidにアクセスしたときにと404が返ること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/userschedules/99"))
        .andExpect(MockMvcResultMatchers.status().is(404));
  }

}
