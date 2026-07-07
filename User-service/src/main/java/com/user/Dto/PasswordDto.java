package com.user.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {

    @NotNull(message = "Userid must not be empty")
    private UUID userId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9]).{8,}$", message = "Password min length must be 8 with one spacial, one uppercase and one lower case latter")
    private String password;
}
