package com.example.music.Repo;


import com.example.music.Entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepo extends JpaRepository<RolesEntity,Integer> {
    Optional<RolesEntity> findByName(String role);

}
