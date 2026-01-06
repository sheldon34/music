package com.example.music.service;

import com.example.music.Dto.merchandiseDto;
import com.example.music.Entity.merchandise;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


//e Long  merchandiseId;
//private String merchandiseName;
//private String merchandisePrice;
//private String merchandiseImg;
//private String merchandiseDetail;
public interface merchandiseService {
    merchandiseDto createMerchandise(String merchandiseName, String merchandisePrice, MultipartFile merchandiseImg, String merchandiseDetails);
    merchandiseDto updateMerchandise(Long merchandiseId, String merchandiseName, String merchandisePrice, MultipartFile merchandiseImg, String merchandiseDetails);
    List<merchandiseDto> getAllMerchandise();
    merchandiseDto getMerchandiseById(Long  merchandiseId);
 void deleteMerchandise(Long merchandiseId);
}
