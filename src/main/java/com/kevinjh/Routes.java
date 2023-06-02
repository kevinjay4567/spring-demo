package com.kevinjh;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Routes {

    private final UserRepository userRepository;

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
}
