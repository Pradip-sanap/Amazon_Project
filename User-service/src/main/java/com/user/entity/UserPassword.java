package com.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "UserPasswords")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPassword {
    @Id
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String password;
}
