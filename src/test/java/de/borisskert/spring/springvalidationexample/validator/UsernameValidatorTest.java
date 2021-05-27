package de.borisskert.spring.springvalidationexample.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UsernameValidatorTest {

    UsernameValidator validator;

    @BeforeEach
    public void setup() throws Exception {
        validator = new UsernameValidator();
    }

    @Test
    public void shouldAcceptNull() throws Exception {
        assertThat(
                validator.isValid(null, null)
        ).isEqualTo(true);
    }

    @Test
    public void shouldAcceptEmptyString() throws Exception {
        assertThat(
                validator.isValid("", null)
        ).isEqualTo(true);
    }

    @Test
    public void shouldAcceptShortLowerCaseString() throws Exception {
        assertThat(
                validator.isValid("abcdefgh", null)
        ).isEqualTo(true);
    }

    @Test
    public void shouldAcceptLongLowerCaseString() throws Exception {
        assertThat(
                validator.isValid("abcdefghijkl", null)
        ).isEqualTo(true);
    }

    @Test
    public void shouldNotAcceptTooShortLowerCaseString() throws Exception {
        assertThat(
                validator.isValid("abcdefg", null)
        ).isEqualTo(false);
    }

    @Test
    public void shouldNotAcceptTooLongLowerCaseString() throws Exception {
        assertThat(
                validator.isValid("abcdefghijklm", null)
        ).isEqualTo(false);
    }

    @Test
    public void shouldNotAcceptContainingDigit() throws Exception {
        assertThat(
                validator.isValid("abcde4fgh", null)
        ).isEqualTo(false);
    }

    @Test
    public void shouldNotAcceptUppercaseCharacters() throws Exception {
        assertThat(
                validator.isValid("abcdeAfgh", null)
        ).isEqualTo(false);
    }
}
