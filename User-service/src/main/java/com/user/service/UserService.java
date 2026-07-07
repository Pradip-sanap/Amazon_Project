package com.user.service;

import com.user.Dto.PasswordDto;
import com.user.Dto.RegisterSuccessDto;
import com.user.Dto.UpdateUserRequestDto;
import com.user.Dto.UserRequestDto;
import com.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    RegisterSuccessDto registerUser(UserRequestDto userDto);

    Boolean createNewPassword(PasswordDto passwordDto);

    User getUserById(UUID userId);

    void deleteUser(UUID userId);

    User updateUser(UUID userId, UpdateUserRequestDto userDto);

    List<User> getAllUsers(int page, int size);

    void test();
}
