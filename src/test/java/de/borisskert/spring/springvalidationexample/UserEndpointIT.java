package de.borisskert.spring.springvalidationexample;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;
import static org.hamcrest.Matchers.not;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class
)
@DirtiesContext
@ActiveProfiles("IT")
class UserEndpointIT {
    public static final Pattern UUID_REGEX = Pattern.compile("^([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})$");

    @LocalServerPort
    Integer serverPort;

    // @formatter:off

    @Test
    public void shouldCreateUser() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "username")
                .put("email", "my@email.com");

        given()
                .port(serverPort)
                .body(requestBody.toString())
                .contentType(ContentType.JSON)

        .when()
                .post("/users")

        .then()
                .statusCode(200)
                .body("name", equalTo("username"))
                .body("email", equalTo("my@email.com"))
                .body("id", matchesRegex(UUID_REGEX))
        ;
    }

    @Test
    public void shouldNotAcceptEmptyBody() throws Exception {
        given()
                .port(serverPort)
                .contentType(ContentType.JSON)

        .when()
                .post("/users")

        .then()
                .statusCode(400)
                .body(not(emptyOrNullString()));
    }

    @Test
    public void shouldNotAcceptMissingEmail() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "my name");

        given()
                .port(serverPort)
                .body(requestBody.toString())
                .contentType(ContentType.JSON)

        .when()
                .post("/users")

        .then()
                .statusCode(400)
                .body(not(emptyOrNullString()));
    }

    @Test
    public void shouldNotAcceptEmptyEmail() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "my name")
                .put("email", "");

        given()
                .port(serverPort)
                .body(requestBody.toString())
                .contentType(ContentType.JSON)

        .when()
                .post("/users")

        .then()
                .statusCode(400)
                .body(not(emptyOrNullString()));
    }

    @Test
    public void shouldNotAcceptEmptyName() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "")
                .put("email", "my@email.com");

        given()
                .port(serverPort)
                .body(requestBody.toString())
                .contentType(ContentType.JSON)

        .when()
                .post("/users")

        .then()
                .statusCode(400)
                .body(not(emptyOrNullString()));
    }

    @Test
    public void shouldNotAcceptMissingName() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("email", "my@email.com");

        given()
                .port(serverPort)
                .body(requestBody.toString())
                .contentType(ContentType.JSON)

        .when()
                .post("/users")

        .then()
                .statusCode(400)
                .body(not(emptyOrNullString()));
    }

    @Test
    public void shouldNotAcceptInvalidName() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "user5name")
                .put("email", "my@email.com");

        given()
                .port(serverPort)
                .body(requestBody.toString())
                .contentType(ContentType.JSON)

        .when()
                .post("/users")

        .then()
                .statusCode(400)
                .body(not(emptyOrNullString()));
    }

    @Test
    public void shouldNotAcceptOccupiedUsername() throws Exception {
        JSONObject requestBody = new JSONObject()
                .put("name", "occupied")
                .put("email", "occupied@email.com");

        given()
                .port(serverPort)
                .body(requestBody.toString())
                .contentType(ContentType.JSON)
                .post("/users");

        given()
                .port(serverPort)
                .body(requestBody.toString())
                .contentType(ContentType.JSON)

        .when()
                .post("/users")

        .then()
                .statusCode(400)
                .body(not(emptyOrNullString()));
    }

    // @formatter:on
}
