package com.user.Dto;

import com.user.contants.Role;
import com.user.contants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Role role;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
