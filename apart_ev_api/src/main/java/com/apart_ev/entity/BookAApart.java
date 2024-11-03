package com.apart_ev.entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.apart_ev.dto.BookAApartDto;
import com.apart_ev.enums.BookApartStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class BookAApart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Long price;
    private BookApartStatus bookApartStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "apart_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Apart apart;

    public BookAApartDto getBookAApartDto() {
        BookAApartDto bookAApartDto = new BookAApartDto();
        bookAApartDto.setId(id);
        bookAApartDto.setDays(days);
        bookAApartDto.setBookApartStatus(bookApartStatus);
        bookAApartDto.setPrice(price);
        bookAApartDto.setToDate(toDate);
        bookAApartDto.setFromDate(fromDate);
        bookAApartDto.setEmail(user.getEmail());
        bookAApartDto.setUsername(user.getName());
        bookAApartDto.setUserId(user.getId());
        bookAApartDto.setApartId(apart.getId());
        return bookAApartDto;
    }

}
