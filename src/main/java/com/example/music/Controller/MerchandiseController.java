package com.example.music.Controller;

import com.example.music.Dto.merchandiseDto;
import com.example.music.service.merchandiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/merchandise/")
@RequiredArgsConstructor
public class MerchandiseController {
    private final merchandiseService merchandiseService;
    @PostMapping("upload")
   ResponseEntity<merchandiseDto> createMerchandise(@RequestParam("merchandiseName") String merchandiseName,
                                                    @RequestParam("merchandisePrice") String merchandisePrice,
                                                   @RequestParam(value = "merchandiseImg", required = false) MultipartFile merchandiseImg,
                                                   @RequestParam("merchandiseDetails") String merchandiseDetails){
       merchandiseDto MerchandiseDto= merchandiseService.createMerchandise(merchandiseName,merchandisePrice,merchandiseImg,merchandiseDetails);
       return new ResponseEntity<>(MerchandiseDto, HttpStatus.CREATED);
   }
   @PatchMapping("update/{id}")
   ResponseEntity<merchandiseDto>updateMerchandise( @PathVariable Long id,
                                                   @RequestParam(value = "merchandiseName",required = false) String merchandiseName,
                                                   @RequestParam(value = "merchandisePrice",required = false) String merchandisePrice,
                                                   @RequestParam(value = "merchandiseImg",required = false) MultipartFile merchandiseImg,
                                                   @RequestParam(value = "merchandiseDetails",required = false) String merchandiseDetails){
        merchandiseDto MerchandiseDto=merchandiseService.updateMerchandise(id,merchandiseName,merchandisePrice, merchandiseImg,merchandiseDetails);
        return new ResponseEntity<>(MerchandiseDto, HttpStatus.OK);
   }
   @GetMapping("/getAll")
   ResponseEntity <List<merchandiseDto>> getAllMerchandise(){
        List<merchandiseDto> MerchandiseDto=merchandiseService.getAllMerchandise();
        return new ResponseEntity<>(MerchandiseDto,HttpStatus.OK);
   }
   @GetMapping("get/{id}")
   ResponseEntity<merchandiseDto> getById(@PathVariable Long id){
        merchandiseDto MerchandiseDto=merchandiseService.getMerchandiseById(id);
        return new ResponseEntity<>(MerchandiseDto,HttpStatus.OK);
   }
   @DeleteMapping("delete/{id}")
   ResponseEntity <String> deleteMerchandise(@PathVariable Long id){
        merchandiseService.deleteMerchandise(id);
        return new ResponseEntity<>("Merchandise Deleted Successfully",HttpStatus.OK);
   }
}
