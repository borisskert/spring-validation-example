package de.borisskert.spring.springvalidationexample;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Create new user",
            tags = "User Creation",
            description = "Sample interface to validate User objects.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(schema = @Schema(implementation = CreateUserRequest.class))
                    }
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User successfully created.",
                            content = {@Content(schema = @Schema(implementation = CreatedUser.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Illegal Create-User request"
                    )
            }
    )
    public CreatedUser postUser(
            @RequestBody @Valid CreateUserRequest createUserRequest
    ) {
        return userService.createUser(createUserRequest);
    }
}
