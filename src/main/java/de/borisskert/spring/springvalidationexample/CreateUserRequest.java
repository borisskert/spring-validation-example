package de.borisskert.spring.springvalidationexample;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class CreateUserRequest {
    private final String name;
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
