package com.example.businessmanagement2.user;


import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
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
  void ユーザーが全件取得できること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);


    JSONAssert.assertEquals("{"
        +"  \"results\": ["
        +" {"
        +"  \"id\": 1,"
        +"  \"companyname\": \"○○○会社\","
        +"  \"username\": \"瀬川\""
        + "}",""
        +" {"
        +"  \"id\": 2,"
        +"  \"companyname\": \"△△△会社\","
        +"  \"username\": \"瀬川2\""
        + "}"
        + " ]"
        + "}", response, JSONCompareMode.STRICT);

  }



}
