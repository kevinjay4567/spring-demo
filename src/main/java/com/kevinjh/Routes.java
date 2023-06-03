package com.kevinjh;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class Routes {

    private final UserRepository userRepository;

    record newUserRequest(
            String name,
            Integer age
    ) {
    }

    public Routes(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<?> getUsers(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return List.of(userRepository.findById(id));
        }
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody newUserRequest request) {
        User user = new User();
        user.setName(request.name);
        user.setAge(request.age);
        userRepository.save(user);
        return new ResponseEntity<>("User create", HttpStatus.OK);
    }
}
