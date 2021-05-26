package de.borisskert.spring.springvalidationexample;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ExampleEndpoint {

    @PostMapping
    public CreatedUser postUser(@RequestBody CreateUserRequest createUserRequest) {
        return createUserRequest.create();
    }
}
