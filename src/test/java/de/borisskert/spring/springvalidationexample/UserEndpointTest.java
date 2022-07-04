package de.borisskert.spring.springvalidationexample;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Pattern;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserEndpointTest {
    public static final Pattern UUID_REGEX = Pattern.compile("^([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})$");

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCreateUser() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "username")
                .put("email", "my@email.com");

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("username")))
                .andExpect(jsonPath("email", equalTo("my@email.com")))
                .andExpect(jsonPath("id", matchesRegex(UUID_REGEX)))
        ;
    }

    @Test
    public void shouldNotAcceptEmptyBody() throws Exception {
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void shouldNotAcceptMissingEmail() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "my name");

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.toString()))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void shouldNotAcceptEmptyEmail() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "my name")
                .put("email", "");

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.toString()))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void shouldNotAcceptEmptyName() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "")
                .put("email", "my@email.com");

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.toString()))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void shouldNotAcceptMissingName() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("email", "my@email.com");

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.toString()))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void shouldNotAcceptInvalidName() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "user5name")
                .put("email", "my@email.com");

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.toString()))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void shouldNotAcceptOccupiedUsername() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "occupied")
                .put("email", "occupied@email.com");

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.toString()))
                .andExpect(status().isOk())
        ;

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.toString()))
                .andExpect(status().is4xxClientError())
        ;
    }
}
