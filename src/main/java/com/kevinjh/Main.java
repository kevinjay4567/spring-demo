package com.kevinjh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/users")
public class Main {

    private final UserRepository userRepository;

    public Main(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    record newUserRequest(
            String name,
            Integer age
    ) {}

    /*@GetMapping
    public List<?> getUsers(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return List.of(userRepository.findById(id));
        }
        return userRepository.findAll();
    }*/

    @PostMapping
    public void createUser(@RequestBody newUserRequest request) {
        User user = new User();
        user.setName(request.name);
        user.setAge(request.age);
        userRepository.save(user);
    }

}
