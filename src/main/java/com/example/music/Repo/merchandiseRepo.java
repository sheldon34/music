package com.example.music.Repo;

import com.example.music.Dto.merchandiseDto;
import com.example.music.Entity.merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface merchandiseRepo extends JpaRepository<merchandise,Long> {
}
