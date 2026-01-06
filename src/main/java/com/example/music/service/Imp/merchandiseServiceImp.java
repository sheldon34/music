package com.example.music.service.Imp;

import com.example.music.Dto.merchandiseDto;
import com.example.music.Entity.merchandise;
import com.example.music.Mapper.marchendiseMapper;
import com.example.music.R2Config.R2MediaService;
import com.example.music.Repo.merchandiseRepo;
import com.example.music.service.merchandiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RequiredArgsConstructor
@Service
public class merchandiseServiceImp implements merchandiseService {
    private final merchandiseRepo merchandiseRepo;
    private final R2MediaService r2MediaService;

    @Override
    public merchandiseDto createMerchandise(String merchandiseName, String merchandisePrice, MultipartFile merchandiseImg, String merchandiseDetails) {


        merchandise Merchandise = new merchandise();
Merchandise.setMerchandiseName(merchandiseName);
Merchandise.setMerchandisePrice(merchandisePrice);
Merchandise.setMerchandiseDetail(merchandiseDetails);
        if (merchandiseImg!=null){
            String Image=r2MediaService.uploadFile(merchandiseImg);
            String ImageUrl= r2MediaService.getPublicUrl(Image);
            Merchandise.setMerchandiseImg(ImageUrl);
        }
        merchandise savedMerchandise=merchandiseRepo.save(Merchandise);
        return marchendiseMapper.mapTomerchandiseDto(savedMerchandise);
    }

    @Override
    public merchandiseDto updateMerchandise(Long merchandiseId, String merchandiseName, String merchandisePrice, MultipartFile merchandiseImg, String merchandiseDetails) {
        merchandise Merchandise= merchandiseRepo.findById(merchandiseId).orElseThrow(()-> new RuntimeException(
                "id not found"
        ));
        Merchandise.setMerchandiseName(merchandiseName);
        Merchandise.setMerchandisePrice(merchandisePrice);
        Merchandise.setMerchandiseDetail(merchandiseDetails);
if (Merchandise.getMerchandiseImg()!=null){
    String newImage=r2MediaService.updateFile(Merchandise.getMerchandiseImg(),merchandiseImg);
    String newImageUrl=r2MediaService.getPublicUrl(newImage);
    Merchandise.setMerchandiseImg(newImageUrl);
}
       merchandise savedMerchandise=merchandiseRepo.save(Merchandise);

        return marchendiseMapper.mapTomerchandiseDto(savedMerchandise);
    }

    @Override
    public List<merchandiseDto> getAllMerchandise() {
        List<merchandise>  merchandises = merchandiseRepo.findAll();
  return merchandises.stream().map(marchendiseMapper:: mapTomerchandiseDto).toList();
    }


    @Override
    public merchandiseDto getMerchandiseById(   Long merchandiseId) {
        merchandise Merchandise=merchandiseRepo.findById(merchandiseId).orElseThrow(()-> new RuntimeException("Merchandise not found"));

        return marchendiseMapper.mapTomerchandiseDto(Merchandise);
    }

    @Override
    public void deleteMerchandise(Long merchandiseId) {
        merchandise Merchandise=merchandiseRepo.findById(merchandiseId).orElseThrow(()-> new RuntimeException("Merchandise not found"));
        if(Merchandise.getMerchandiseImg()!=null){
            r2MediaService.deleteFile(Merchandise.getMerchandiseImg());
        }
        merchandiseRepo.deleteById(merchandiseId);

    }

//    @Override
//    public merchandiseDto deleteMerchandise(Long merchandiseId) {
//
//        return null;
//    }
}
