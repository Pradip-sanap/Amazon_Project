package com.user.service.Impl;

import com.user.Dto.UserRequestDto;
import com.user.Exception.EmailAlreadyExistsException;
import com.user.Exception.PhoneAlreadyExistsException;
import com.user.contants.Status;
import com.user.entity.User;
import com.user.repo.UserRepository;
import com.user.service.UserService;
import com.user.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Boolean registerUser(UserRequestDto userDto) {
        User userEntity = userMapper.toEntity(userDto);
        userEntity.setStatus(Status.DETAILS_SUBMITTED);

        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + userEntity.getEmail());
        }
        if (userRepository.existsByPhone(userEntity.getPhone())) {
            throw new PhoneAlreadyExistsException("Phone already exists: " + userEntity.getPhone());
        }

        User save = userRepository.save(userEntity);
        return true;
    }
}
