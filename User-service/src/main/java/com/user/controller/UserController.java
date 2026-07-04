package com.user.controller;

import com.user.Dto.UserRequestDto;
import com.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Boolean registeredUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Registration request start :)");
        boolean success = userService.registerUser(userRequestDto);
        return success;

    }

}
