package com.user.entity;

import com.user.Dto.UpdateUserRequestDto;
import com.user.contants.Role;
import com.user.contants.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private LocalDate DoB;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void update(UpdateUserRequestDto dto) {
        if (dto.getFirstName() != null)
            this.firstName = dto.getFirstName();

        if (dto.getLastName() != null)
            this.lastName = dto.getLastName();

        if (dto.getEmail() != null)
            this.email = dto.getEmail();

        if (dto.getPhone() != null)
            this.phone = dto.getPhone();

        if (dto.getRole() != null)
            this.role = dto.getRole();

        if (dto.getAge() != null)
            this.age = dto.getAge();

        if (dto.getDoB() != null)
            this.DoB = dto.getDoB();
    }
}
