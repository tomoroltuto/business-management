package com.example.businessmanagement.performance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DataSet(value = "performance/datasets/performances.yml")
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PerformanceApiIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @Transactional
  void 作業実績が全件取得に成功すると200で内容を返すこと() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/performances"))
        .andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  @Transactional
  void 作業実績が全件取得できること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/performances"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
            "results": [
                {
                    "performanceId": 1,
                    "userId": 1,
                    "workingDate": "2022-12-06",
                    "place": "4階トイレ",
                    "workContent": "墨出し",
                    "numberOfPeople": 3
                },
                {
                    "performanceId": 2,
                    "userId": 2,
                    "workingDate": "2022-12-07",
                    "place": "3階和室",
                    "workContent": "配線",
                    "numberOfPeople": 5
                },
                {
                    "performanceId": 3,
                    "userId": 2,
                    "workingDate": "2022-12-10",
                    "place": "3階トイレ２",
                    "workContent": "BOX取り付け",
                    "numberOfPeople": 2
                },
                {
                    "performanceId": 4,
                    "userId": 3,
                    "workingDate": "2022-12-09",
                    "place": "4階洋室",
                    "workContent": "配管",
                    "numberOfPeople": 4
                }
            ]
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 存在する作業実績のIDを指定したとき正常に作業実績が返されること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/performances/2"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
            "performanceId": 2,
            "userId": 2,
            "workingDate": "2022-12-07",
            "place": "3階和室",
            "workContent": "配線",
            "numberOfPeople": 5
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 存在しない作業実績のidにアクセスしたときにと404が返ること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/performances/99"))
        .andExpect(MockMvcResultMatchers.status().is(404));
  }

  @Test
  @Transactional
  void 検索時に該当するIDの作業実績がいないときエラーメッセージが返ること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/performances/99"))
        .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
            "title": "Resource Not Found",
            "detail": "PerformanceEntity (id = 99) is not found."
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 作業実績の登録に成功すると201とLocationヘッダーとレスポンスメッセージを返すこと() throws Exception {
    String response = mockMvc.perform(
            MockMvcRequestBuilders.post("/performances").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "userId": 3,
                            "workingDate": "2022-12-30",
                            "place": "トイレ",
                            "workContent": "墨出し",
                            "numberOfPeople": "3"
                        }
                    """))
        .andExpect(MockMvcResultMatchers.status().is(201))
        .andExpect(header().string("Location", "http://localhost/performances/5"))
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
          "message": "作業実績を登録しました"
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 作業実績登録時未来の日付入力した場合エラーメッセージを返すこと() throws Exception {
    String response = mockMvc.perform(
            MockMvcRequestBuilders.post("/performances").contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                                "userId": 3,
                                "workingDate": "2023-12-11",
                                "place": "トイレ",
                                "workContent": "墨出し",
                                "numberOfPeople": "3"
                            }
                    """)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn()
        .getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
          {
              "title": "Bad Request",
              "detail": "リクエストが不正です。正しいリクエストでリトライしてください",
              "invalidParams": [
                  {
                      "name": "workingDate",
                      "reason": "must be a date in the past or in the present"
                  }
              ]
          }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 作業実績登録時文字数が256文字以上の場合エラーメッセージを返すこと() throws Exception {
    String response = mockMvc.perform(
            MockMvcRequestBuilders.post("/performances").contentType(MediaType.APPLICATION_JSON)
                .content("""
                      {
                          "userId": 3,
                          "workingDate": "2022-12-30",
                          "place": "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあい",
                          "workContent": "墨出し",
                          "numberOfPeople": "3"
                      }
                    """)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn()
        .getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
          {
              "title": "Bad Request",
              "detail": "リクエストが不正です。正しいリクエストでリトライしてください",
              "invalidParams": [
                  {
                      "name": "place",
                      "reason": "size must be between 0 and 256"
                  }
              ]
          }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 作業実績登録時空白がある場合エラーメッセージを返すこと() throws Exception {
    String response = mockMvc.perform(
            MockMvcRequestBuilders.post("/performances").contentType(MediaType.APPLICATION_JSON)
                .content("""
                      {
                          "userId": 3,
                          "workingDate": "2022-12-30",
                          "place": "4階部屋",
                          "workContent": "",
                          "numberOfPeople": "3"
                      }
                    """)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn()
        .getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
          {
              "title": "Bad Request",
              "detail": "リクエストが不正です。正しいリクエストでリトライしてください",
              "invalidParams": [
                  {
                      "name": "workContent",
                      "reason": "must not be blank"
                  }
              ]
          }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 作業実績更新に成功すると200とレスポンスメッセージを返すこと() throws Exception {
    String response = mockMvc.perform(
            MockMvcRequestBuilders.patch("/performances/1").contentType(MediaType.APPLICATION_JSON)
                .content("""
                      {
                          "performancesId": 1,
                          "userId": 1,
                          "workingDate": "2022-12-30",
                          "place": "4階",
                          "workContent": "墨出し",
                          "numberOfPeople": "3"
                      }
                    """)).andExpect(MockMvcResultMatchers.status().is(200)).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
        "message": "作業実績を更新しました"
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 作業実績更新時に該当するIDの作業予定がいないときエラーメッセージを返すこと() throws Exception {

    String response = mockMvc.perform(
            MockMvcRequestBuilders.patch("/performances/99").contentType(MediaType.APPLICATION_JSON)
                .content("""
                      {
                          "userId": 1,
                          "workingDate": "2022-12-30",
                          "place": "4階",
                          "workContent": "墨出し",
                          "numberOfPeople": "3"
                      }
                    """)).andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn()
        .getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
        "title": "Resource Not Found",
        "detail": "PerformanceEntity (id = 99) is not found."
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 作業実績更新時文字数が256文字以上の場合エラーメッセージを返すこと() throws Exception {

    String response = mockMvc.perform(
            MockMvcRequestBuilders.patch("/performances/1").contentType(MediaType.APPLICATION_JSON)
                .content("""
                      {
                          "userId": 1,
                          "workingDate": "2022-12-30",
                          "place": "4階",
                          "workContent": "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあい",
                          "numberOfPeople": "4"
                      }
                    """))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
          {
              "title": "Bad Request",
              "detail": "リクエストが不正です。正しいリクエストでリトライしてください",
              "invalidParams": [
                  {
                      "name": "workContent",
                      "reason": "size must be between 0 and 256"
                  }
              ]
          }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 作業実績更新時人数が200以上の場合エラーメッセージを返すこと() throws Exception {

    String response = mockMvc.perform(
            MockMvcRequestBuilders.patch("/performances/1").contentType(MediaType.APPLICATION_JSON)
                .content("""
                      {
                          "userId": 1,
                          "workingDate": "2022-12-30",
                          "place": "4階",
                          "workContent": "墨出し",
                          "numberOfPeople": "201"
                      }
                    """)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn()
        .getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
          {
              "title": "Bad Request",
              "detail": "リクエストが不正です。正しいリクエストでリトライしてください",
              "invalidParams": [
                  {
                      "name": "numberOfPeople",
                      "reason": "must be less than or equal to 200"
                  }
              ]
          }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 指定した作業実績を1件削除できること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/performances/1"))
        .andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);
  }

  @Test
  @Transactional
  void 削除時に該当するIDの作業実績がいないときエラーメッセージが返ること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.delete("/performances/99"))
        .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
        "title": "Resource Not Found",
        "detail": "PerformanceEntity (id = 99) is not found."
        }
        """, response, JSONCompareMode.STRICT);
  }

}

