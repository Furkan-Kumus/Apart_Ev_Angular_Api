package com.apart_ev.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apart_ev.dto.ApartDto;
import com.apart_ev.dto.BookAApartDto;
import com.apart_ev.dto.SearchApartDto;
import com.apart_ev.services.customer.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/aparts")
    public ResponseEntity<List<ApartDto>> getAllAparts() {
        List<ApartDto> apartDtoList = customerService.getAllAparts();
        return ResponseEntity.ok(apartDtoList);
    }

    @PostMapping("/apart/book/{apartId}")
    public ResponseEntity<Void> bookAApart(@PathVariable Long apartId, @RequestBody BookAApartDto bookAApartDto) {
        boolean success = customerService.BookAApart(bookAApartDto);
        if (success)
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/apart/{apartId}")
    public ResponseEntity<ApartDto> getApartById(@PathVariable Long apartId) {
        ApartDto apartDto = customerService.getApartById(apartId);
        if (apartDto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(apartDto);
    }

    @GetMapping("/apart/bookings/{apartId}")
    public ResponseEntity<List<BookAApartDto>> getBookingsByUserId(@PathVariable Long apartId) {
        return ResponseEntity.ok(customerService.getBookingsByUserId(apartId));
    }

    @PostMapping("/apart/search")
    public ResponseEntity<?> searchApart(@RequestBody SearchApartDto searchApartDto) {
        return ResponseEntity.ok(customerService.searchApart(searchApartDto));
    }
}
