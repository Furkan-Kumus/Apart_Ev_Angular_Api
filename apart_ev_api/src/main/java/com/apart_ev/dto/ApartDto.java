package com.apart_ev.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ApartDto {

    private Long id;

    private String brand;

    private String color;

    private String name;

    private String type;

    private String transmission;

    private String description;

    private Long price;

    private Date year;

    private MultipartFile image;

    private byte[] returnedImage;
}
