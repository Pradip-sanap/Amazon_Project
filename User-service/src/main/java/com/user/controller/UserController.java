package com.user.controller;

import com.user.Dto.LoginDto;
import com.user.Dto.PasswordDto;
import com.user.Dto.UpdateUserRequestDto;
import com.user.Dto.UserRequestDto;
import com.user.entity.User;
import com.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registeredUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Registration request start :)");
        return new ResponseEntity<>(userService.registerUser(userRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/createpassword")
    public ResponseEntity<?> createPassword(@Valid @RequestBody PasswordDto passwordDto){
        log.info("Request for New Password Create");
        if(userService.createNewPassword(passwordDto)){
            return new ResponseEntity<>("Successfully saved Password", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create Password", HttpStatus.CREATED);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto){

    }

    @GetMapping
    public ResponseEntity<?> getUser(@RequestParam("userId") UUID userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestParam("userId") UUID userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateUserDetails(@RequestParam UUID userId,@Valid @RequestBody UpdateUserRequestDto userDto) {
        log.info("Request for Update user: {}" , userDto);
        log.info("UUID: {}", userId);
        return new ResponseEntity<>(userService.updateUser(userId, userDto), HttpStatus.CREATED);
    }

    @GetMapping("page/{page}/size/{size}")
    public ResponseEntity<?> getAllUsers(@PathVariable int page, @PathVariable int size) {
        return new ResponseEntity<>(userService.getAllUsers(page, size), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        userService.test();
        return null;
    }

}
