package de.borisskert.spring.springvalidationexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserEndpoint {

    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public CreatedUser postUser(
            @RequestBody @Valid CreateUserRequest createUserRequest
    ) {
        return userService.createUser(createUserRequest);
    }
}
