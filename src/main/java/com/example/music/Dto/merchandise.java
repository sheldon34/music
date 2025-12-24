package com.example.music.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class merchandise {
    private Long  merchandiseId;
    private String merchandiseName;
    private String merchandisePrice;
    private String merchandiseImg;
    private String merchandiseDetail;
}
