package com.apart_ev.services.customer;

import java.util.List;

import com.apart_ev.dto.ApartDto;
import com.apart_ev.dto.BookAApartDto;

public interface CustomerService {
    List<ApartDto> getAllAparts();

    boolean BookAApart(BookAApartDto bookAApartDto);

    ApartDto getApartById(Long apartId);
}
