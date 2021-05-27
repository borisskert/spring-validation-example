package de.borisskert.spring.springvalidationexample.validator;

import de.borisskert.spring.springvalidationexample.CreateUserRequest;
import de.borisskert.spring.springvalidationexample.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UnoccupiedUsernameValidatorTest {

    @Autowired
    UserService userService;

    @Autowired
    UnoccupiedUsernameValidator validator;

    @Test
    public void shouldAcceptNull() throws Exception {
        assertThat(validator.isValid(null, null)).isEqualTo(true);
    }

    @Test
    public void shouldAcceptEmpty() throws Exception {
        assertThat(validator.isValid("", null)).isEqualTo(true);
    }

    @Test
    public void shouldAcceptUnoccupiedUsername() throws Exception {
        assertThat(validator.isValid("unoccupied", null)).isEqualTo(true);
    }

    @Test
    public void shouldNotAcceptOccupiedUsername() throws Exception {
        userService.createUser(new CreateUserRequest("occupied", "my@email.com"));
        assertThat(validator.isValid("occupied", null)).isEqualTo(false);
    }
}
