package com.example.tuxBack.domain.repository;

import com.example.tuxBack.domain.entity.UserEntity;
import com.example.tuxBack.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String userEmail);
    boolean existsByEmail(String email);
    boolean existsById(String id);
    boolean existsByStudentNum(String studentNum);
    Optional<UserEntity> findById(String id);
}
