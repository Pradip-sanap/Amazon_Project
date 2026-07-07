package com.user.mapper;

import com.user.Dto.PasswordDto;
import com.user.Dto.RegisterSuccessDto;
import com.user.Dto.UserRequestDto;
import com.user.Dto.UserResponseDto;
import com.user.entity.User;
import com.user.entity.UserPassword;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto){
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .role(dto.getRole())
                .age(dto.getAge())
                .DoB(dto.getDoB())
                .build();
    }

    public UserResponseDto toUserResponseDto(User user){
        return UserResponseDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(user.getStatus())
                .role(user.getRole())
                .age(user.getAge())
                .DoB(user.getDoB())
                .build();
    }

    public RegisterSuccessDto toRegisterSuccessDto(User user){
        return RegisterSuccessDto.builder()
                .id(user.getUserId())
                .message("User is Successfully Register. Next step: Create Password.")
                .build();
    }

    public UserPassword toUserPasswordEntity(PasswordDto dto){
        return UserPassword.builder()
                .userId(dto.getUserId())
                .password((dto.getPassword()))
                .build();
    }


}
