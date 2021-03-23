package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.borodkin.elibrary.services.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/rest/user")
    public UserDetails get() {
        return userService.getCurrentUser();
    }

}
