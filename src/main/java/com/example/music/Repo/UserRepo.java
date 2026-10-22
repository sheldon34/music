package com.example.music.Repo;

import com.example.music.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

Optional<UserEntity> findByUsername(String username );
Boolean  existsByUsername(String username);
Optional<UserEntity> findById(Long userId);
}
