package com.example.businessmanagement2.user;


import com.example.businessmanagement2.restcontroller.user.UserForm;
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


@SpringBootTest
@DataSet(value = "datasets/users.yml")
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
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"results\": [" +
        "{" +
        "\"id\": 1," +
        "\"companyname\": \"○○○会社\"," +
        "\"username\": \"瀬川\"" +
        "}," +
        "{" +
        "\"id\": 2," +
        "\"companyname\": \"△△△会社\"," +
        "\"username\": \"瀬川2\"" +
        "}" +
        "]" +
        "}", response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 存在するユーザのIDを指定したとき正常にユーザーが返されること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/users/2"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"id\": 2," +
        "\"companyname\": \"△△△会社\"," +
        "\"username\": \"瀬川2\"" +
        "}" +
        "]" +
        "}", response, JSONCompareMode.STRICT);
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
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"title\": \"Resource Not Found\"," +
        "\"detail\": \"UserEntity (id = 99) is not found.\"" +
        "}", response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー登録に成功すると201とレスポンスメッセージを返すこと() throws Exception {
    UserForm Uf = new UserForm("xxx会社", "瀬川3");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(Uf);

    String response = mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().is(201))
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"message\": \"ユーザーを登録しました\"" +
        "}", response, JSONCompareMode.STRICT);
  }
  @Test
  @Transactional
  void ユーザー登録時空文字nullの場合エラーメッセージを返すこと() throws Exception {
    UserForm Uf = new UserForm(null, "瀬川");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(Uf);

    String response = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                    .contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
            "\"title\": \"Bad Request\"," +
            "\"detail\": \"リクエストが不正です。正しいリクエストでリトライしてください\"," +
            "\"invalidParams\": [" +
            "{" +
            "\"name\": \"companyname\"," +
            "\"reason\": \"must not be blank\"" +
            "}" +
            "]" +
            "}", response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー登録時文字数が256文字以上の場合エラーメッセージを返すこと() throws Exception {
    UserForm Uf = new UserForm(
        "xxx会社",
        "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
            + "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
            + "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
            + "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
            + "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
            + "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
            + "あいうえおあいうえおあいうえおあいうえおあい");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(Uf);

    String response = mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"title\": \"Bad Request\"," +
        "\"detail\": \"リクエストが不正です。正しいリクエストでリトライしてください\"," +
        "\"invalidParams\": [" +
        "{" +
        "\"name\": \"username\"," +
        "\"reason\": \"size must be between 1 and 256\"" +
        "}" +
        "]" +
        "}", response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー更新に成功すると200とレスポンスメッセージを返すこと() throws Exception {

    UserForm Uf = new UserForm("〇〇会社", "瀬川1");
    Uf.setCompanyname("XX会社");
    Uf.setUsername("瀬川3");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(Uf);

    String response = mockMvc.perform(MockMvcRequestBuilders.patch("/users/1")
            .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"message\": \"ユーザーを更新しました\"" +
        "}", response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー更新時に該当するIDのユーザーがいないときエラーメッセージを返すこと() throws Exception {

    UserForm Uf = new UserForm("〇〇会社", "瀬川1");
    Uf.setCompanyname("XX会社");
    Uf.setUsername("瀬川3");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(Uf);

    String response = mockMvc.perform(MockMvcRequestBuilders.patch("/users/99")
            .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"title\": \"Resource Not Found\"," +
        "\"detail\": \"UserEntity (id = 99) is not found.\"" +
        "}", response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー更新時空文字nullの場合エラーメッセージを返すこと() throws Exception {

    UserForm Uf = new UserForm("〇〇会社", "瀬川1");
    Uf.setCompanyname("XX会社");
    Uf.setUsername(null);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(Uf);

    String response = mockMvc.perform(MockMvcRequestBuilders.patch("/users/1")
            .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"title\": \"Bad Request\"," +
        "\"detail\": \"リクエストが不正です。正しいリクエストでリトライしてください\"," +
        "\"invalidParams\": [" +
        "{" +
        "\"name\": \"username\"," +
        "\"reason\": \"must not be blank\"" +
        "}" +
        "]" +
        "}", response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー更新時文字数が256文字以上の場合エラーメッセージを返すこと() throws Exception {

    UserForm Uf = new UserForm("〇〇会社", "瀬川1");
    Uf.setCompanyname("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
        + "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
        + "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
        + "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
        + "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお"
        + "あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあい");
    Uf.setUsername("瀬川3");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(Uf);

    String response = mockMvc.perform(MockMvcRequestBuilders.patch("/users/1")
            .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"title\": \"Bad Request\"," +
        "\"detail\": \"リクエストが不正です。正しいリクエストでリトライしてください\"," +
        "\"invalidParams\": [" +
        "{" +
        "\"name\": \"companyname\"," +
        "\"reason\": \"size must be between 1 and 256\"" +
        "}," +
        "{" +
        "\"name\": \"username\"," +
        "\"reason\": \"size must be between 1 and 256\"" +
        "}" +
        "]" +
        "}", response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void 指定したデーターを1件削除できること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
        .andExpect(MockMvcResultMatchers.status().isNoContent())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
  }

  @Test
  @Transactional
  void 削除時に該当するIDのユーザーがいないときエラーメッセージが返ること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.delete("/users/99"))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"title\": \"Resource Not Found\"," +
        "\"detail\": \"UserEntity (id = 99) is not found.\"" +
        "}", response, JSONCompareMode.STRICT);
  }
}



