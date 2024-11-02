package com.apart_ev.entity;

import java.util.Date;

import com.apart_ev.dto.ApartDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "aparts")
public class Apart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String color;

    private String name;

    private String type;

    private String transmission;

    private String description;

    private Long price;

    private Date year;

    @Column(columnDefinition = "longblob")
    private byte[] image;

    public ApartDto geApartDto() {
        ApartDto apartDto = new ApartDto();
        apartDto.setId(id);
        apartDto.setName(name);
        apartDto.setBrand(brand);
        apartDto.setColor(color);
        apartDto.setPrice(price);
        apartDto.setDescription(description);
        apartDto.setType(type);
        apartDto.setTransmission(transmission);
        apartDto.setYear(year);
        apartDto.setReturnedImage(image);

        return apartDto;
    }

}
