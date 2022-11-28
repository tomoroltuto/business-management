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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@DataSet(value="datasets/users.yml")
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRestApiIntegration {

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
    "}",response, JSONCompareMode.STRICT);
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
        "}",response, JSONCompareMode.STRICT);
  }
  @Test
  @Transactional
  void 存在しないユーザーのidにアクセスしたときにと404が返ること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users/99"))
        .andExpect(MockMvcResultMatchers.status().is(404));
  }


  @Test
  @Transactional
  void 存在しないユーザーのidにアクセスしたときにとエラーメッセージが返ること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/users/99"))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("{" +
        "\"title\": \"Resource Not Found\"," +
        "\"detail\": \"UserEntity (id = 99) is not found.\"" +
        "}",response, JSONCompareMode.STRICT);
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
        "}",response, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  void ユーザー登録時をnullでリクエスト400とエラーメッセージを返すこと() throws Exception {
    UserForm Uf = new UserForm(null, null);

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
        "}," +
        "{" +
        "\"name\": \"username\"," +
        "\"reason\": \"must not be blank\"" +
        "}" +
        "]" +
        "}",response, JSONCompareMode.STRICT);
  }

}



