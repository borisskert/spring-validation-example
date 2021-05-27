package de.borisskert.spring.springvalidationexample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.borisskert.spring.springvalidationexample.validator.UnoccupiedUsername;
import de.borisskert.spring.springvalidationexample.validator.Username;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CreateUserRequest {
    @NotNull
    @NotEmpty
    @Username
    @UnoccupiedUsername
    private final String name;

    @NotNull
    @NotEmpty
    private final String email;

    public CreateUserRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @JsonIgnore
    public CreatedUser create() {
        return new CreatedUser(UUID.randomUUID().toString(), name, email);
    }
}
