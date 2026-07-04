package com.user.repo;

import com.user.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserPasswordRepository extends JpaRepository<UserPassword, UUID> {
}
