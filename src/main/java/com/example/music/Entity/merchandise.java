package com.example.music.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "merchandise")
public class merchandise {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  merchandiseId;
    private String merchandiseName;
    private String merchandisePrice;
    private String merchandiseImg;
    private String merchandiseDetail;
}
