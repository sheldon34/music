package com.example.music.Repo;


import com.example.music.Entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<RolesEntity,Integer> {
    Optional<RolesEntity> findByName(String role);

}
