package de.borisskert.spring.springvalidationexample;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserEndpoint {

    @PostMapping
    public CreatedUser postUser(
            @RequestBody @Valid CreateUserRequest createUserRequest
    ) {
        return createUserRequest.create();
    }
}
