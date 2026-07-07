package com.user.service.Impl;

import com.user.Dto.PasswordDto;
import com.user.Dto.RegisterSuccessDto;
import com.user.Dto.UpdateUserRequestDto;
import com.user.Dto.UserRequestDto;
import com.user.Exception.EmailAlreadyExistsException;
import com.user.Exception.PhoneAlreadyExistsException;
import com.user.Exception.UserNotFoundException;
import com.user.contants.Status;
import com.user.entity.User;
import com.user.entity.UserPassword;
import com.user.repo.UserPasswordRepository;
import com.user.repo.UserRepository;
import com.user.service.UserService;
import com.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserPasswordRepository userPasswordRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public RegisterSuccessDto registerUser(UserRequestDto userDto) {
        User userEntity = userMapper.toEntity(userDto);
        userEntity.setStatus(Status.DETAILS_SUBMITTED);

        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + userEntity.getEmail());
        }
        if (userRepository.existsByPhone(userEntity.getPhone())) {
            throw new PhoneAlreadyExistsException("Phone already exists: " + userEntity.getPhone());
        }

        User savedUser = userRepository.save(userEntity);
        return userMapper.toRegisterSuccessDto(savedUser);
    }

    @Override
    public Boolean createNewPassword(PasswordDto passwordDto) {
        try{
            User user = getUserById(passwordDto.getUserId());
            passwordDto.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
            UserPassword userPasswordEntity = userMapper.toUserPasswordEntity(passwordDto);

            UserPassword saved = userPasswordRepository.save(userPasswordEntity);
            return true;
        } catch (UserNotFoundException e) {
            throw new IllegalArgumentException("Invalid UserId provided : "+ passwordDto.getUserId());
        }

    }

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User Not Found for userId: " + userId));

    }

    @Override
    public void deleteUser(UUID userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
        return;
    }

    @Override
    public User updateUser(UUID userId, UpdateUserRequestDto userDto) {
        User user = getUserById(userId);
        user.update(userDto);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName").ascending());
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent();
    }

    @Override
    public void test() {

        User u = User.builder()
                .firstName("ravi")
                .lastName("sanap")
                .email("ravi@gmail.com")
                .build();
        userRepository.save(u);
        return;

    }
}
