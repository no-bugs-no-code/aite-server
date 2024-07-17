package com.example.aiet_server.domain.user.repository;

import com.example.aiet_server.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findById(String id);
}
