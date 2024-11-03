package com.apart_ev.dto;

import java.util.Date;

import com.apart_ev.enums.BookApartStatus;

import lombok.Data;

@Data
public class BookAApartDto {
    private Long id;
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Long price;
    private BookApartStatus bookApartStatus;
    private Long apartId;
    private Long userId;
    private String username;
    private String email;
}
