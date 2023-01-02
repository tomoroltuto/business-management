package com.example.businessmanagement2.user;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import com.example.businessmanagement2.controller.user.UserForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@SpringBootTest
@DataSet(value = "user/datasets/users.yml")
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRestApiIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @Transactional
  void ユーザーが全件取得に成功すると200で内容を返すこと() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users"))
        .andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  @Transactional
  void ユーザーが全件取得できること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
            "results": [
                {
                    "userId": 1,
                    "companyName": "○○○会社",
                    "userName": "瀬川"
                },
                {
                    "userId": 2,
                    "companyName": "△△△会社",
                    "userName": "瀬川2"
                },
                {
                    "userId": 3,
                    "companyName": "xxx会社",
                    "userName": "瀬川3"
                }
            ]
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 存在するユーザのIDを指定したとき正常にユーザーが返されること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/users/2"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
            "userId": 2,
            "companyName": "△△△会社",
            "userName": "瀬川2"
        }     
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 存在しないユーザーのidにアクセスしたときにと404が返ること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users/99"))
        .andExpect(MockMvcResultMatchers.status().is(404));
  }


  @Test
  @Transactional
  void 検索時に該当するIDのユーザーがいないときエラーメッセージが返ること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/users/99"))
        .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
            "title": "Resource Not Found",
            "detail": "UserEntity (id = 99) is not found."
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー登録に成功すると201とLocationヘッダーとレスポンスメッセージを返すこと() throws Exception {
    UserForm uf = new UserForm("yyy会社", "瀬川4");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(uf);

    String response = mockMvc.perform(
            MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().is(201))
        .andExpect(header().string("Location", "http://localhost/users/5"))
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
          "message": "ユーザーを登録しました"
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー登録時空文字nullの場合エラーメッセージを返すこと() throws Exception {
    UserForm uf = new UserForm(null, "瀬川");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(uf);

    String response = mockMvc.perform(
            MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
         {
           "title": "Bad Request",
           "detail": "リクエストが不正です。正しいリクエストでリトライしてください",
           "invalidParams": [
               {
                 "name": "companyName",
                 "reason": "must not be blank"
               }
           ]
         }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー登録時文字数が256文字以上の場合エラーメッセージを返すこと() throws Exception {
    UserForm uf = new UserForm("xxx会社", """
        あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
        あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
        あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
        あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
        あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
        あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
        あいうえおあいうえおあいうえおあいうえおあい
        """);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(uf);

    String response = mockMvc.perform(
            MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
         {
           "title": "Bad Request",
           "detail": "リクエストが不正です。正しいリクエストでリトライしてください",
           "invalidParams": [
               {
                 "name": "userName",
                 "reason": "size must be between 1 and 256"
               }
           ]
         }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー更新に成功すると200とレスポンスメッセージを返すこと() throws Exception {

    UserForm uf = new UserForm("〇〇会社", "瀬川1");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(uf);

    String response = mockMvc.perform(
            MockMvcRequestBuilders.patch("/users/1").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().is(200)).andReturn()
        .getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
        "message": "ユーザーを更新しました"
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー更新時に該当するIDのユーザーがいないときエラーメッセージを返すこと() throws Exception {

    UserForm uf = new UserForm("XX会社", "瀬川3");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(uf);

    String response = mockMvc.perform(
            MockMvcRequestBuilders.patch("/users/99").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn()
        .getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
        "title": "Resource Not Found",
        "detail": "UserEntity (id = 99) is not found."
        }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー更新時空文字nullの場合エラーメッセージを返すこと() throws Exception {

    UserForm uf = new UserForm("XX会社", null);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(uf);

    String response = mockMvc.perform(
            MockMvcRequestBuilders.patch("/users/1").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn()
        .getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
         {
           "title": "Bad Request",
           "detail": "リクエストが不正です。正しいリクエストでリトライしてください",
           "invalidParams": [
               {
                 "name": "userName",
                 "reason": "must not be blank"
               }
           ]
         }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー更新時文字数が256文字以上の場合エラーメッセージを返すこと() throws Exception {

    UserForm uf = new UserForm("""
          あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
          あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
          あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
          あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
          あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
          あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお
          あいうえおあいうえおあいうえおあいうえおあい                                    
        """, "瀬川3");
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(uf);

    String response = mockMvc.perform(
            MockMvcRequestBuilders.patch("/users/1").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn()
        .getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
         {
           "title": "Bad Request",
           "detail": "リクエストが不正です。正しいリクエストでリトライしてください",
           "invalidParams": [
               {
                 "name": "companyName",
                 "reason": "size must be between 1 and 256"
               }
           ]
         }
        """, response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 指定したデーターを1件削除できること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
        .andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);
  }

  @Test
  @Transactional
  void 削除時に該当するIDのユーザーがいないときエラーメッセージが返ること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.delete("/users/99"))
        .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn().getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
        "title": "Resource Not Found",
        "detail": "UserEntity (id = 99) is not found."
        }
        """, response, JSONCompareMode.STRICT);
  }
}

