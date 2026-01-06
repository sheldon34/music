package com.example.music.Mapper;

import com.example.music.Dto.merchandiseDto;
import com.example.music.Entity.merchandise;

public class marchendiseMapper {

public static merchandise mapTomerchandise (merchandiseDto merchandiseDto){
      merchandise merchandise = new merchandise();
      merchandise.setMerchandiseId(merchandiseDto.getMerchandiseId());
      merchandise.setMerchandiseImg(merchandiseDto.getMerchandiseImg());
      merchandise.setMerchandiseName(merchandiseDto.getMerchandiseName());
      merchandise.setMerchandiseDetail(merchandiseDto.getMerchandiseDetail());
      merchandise.setMerchandisePrice(merchandiseDto.getMerchandisePrice());



    return merchandise;
}
public static merchandiseDto mapTomerchandiseDto (merchandise merchandise){
    merchandiseDto merchandiseDto = new merchandiseDto();
    merchandiseDto.setMerchandiseId(merchandise.getMerchandiseId());
    merchandiseDto.setMerchandiseName(merchandise.getMerchandiseName());
    merchandiseDto.setMerchandiseDetail(merchandise.getMerchandiseDetail());
    merchandiseDto.setMerchandisePrice(merchandise.getMerchandisePrice());
    merchandiseDto.setMerchandiseImg(merchandise.getMerchandiseImg());
    return merchandiseDto;


}

}
