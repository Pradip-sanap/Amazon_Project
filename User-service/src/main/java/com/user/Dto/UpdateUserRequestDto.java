package com.user.Dto;

import com.user.contants.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequestDto {

    @Size(min = 3, max = 50)
    private String firstName;

    @Size(min = 3, max = 50)
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid PhoneNumber" )
    private String phone;

    private Role role;

    @Positive
    @Min(value = 18, message = "Age must be >= 18")
    @Max(value = 100, message = "Age must be <= 100")
    private Integer age;

    @Past(message = "Birthdate must be in the past")
    private LocalDate DoB;
}

