package com.example.music.Repo;

import com.example.music.Entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface trackRepo extends JpaRepository<Track,Long> {
}
