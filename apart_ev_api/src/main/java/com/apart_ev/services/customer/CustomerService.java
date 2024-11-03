package com.apart_ev.services.customer;

import java.util.List;

import com.apart_ev.dto.ApartDto;
import com.apart_ev.dto.ApartDtoListDto;
import com.apart_ev.dto.BookAApartDto;
import com.apart_ev.dto.SearchApartDto;

public interface CustomerService {
    List<ApartDto> getAllAparts();

    boolean BookAApart(BookAApartDto bookAApartDto);

    ApartDto getApartById(Long apartId);

    List<BookAApartDto> getBookingsByUserId(Long userId);

    ApartDtoListDto searchApart(SearchApartDto searchApartDto);
}
