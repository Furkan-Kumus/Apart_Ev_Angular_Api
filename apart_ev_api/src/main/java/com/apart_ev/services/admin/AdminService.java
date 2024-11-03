package com.apart_ev.services.admin;

import java.io.IOException;
import java.util.List;

import com.apart_ev.dto.ApartDto;
import com.apart_ev.dto.ApartDtoListDto;
import com.apart_ev.dto.BookAApartDto;
import com.apart_ev.dto.SearchApartDto;

public interface AdminService {

    boolean postApart(ApartDto apartDto) throws IOException;

    List<ApartDto> getAllAparts();

    void deleteApart(Long id);

    ApartDto getApartById(Long id);

    boolean updateApart(Long apartId, ApartDto apartDto) throws IOException;

    List<BookAApartDto> getBookings();

    boolean changeBookingStatus(Long bookingId, String status);

    ApartDtoListDto searchApart(SearchApartDto searchApartDto);
}
