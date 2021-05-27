package de.borisskert.spring.springvalidationexample;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final Map<String, CreatedUser> users = new HashMap<>();

    public CreatedUser createUser(CreateUserRequest createUserRequest) {
        CreatedUser createdUser = createUserRequest.create();

        users.put(createdUser.getName(), createdUser);

        return createdUser;
    }

    public Optional<CreatedUser> getUserByName(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
