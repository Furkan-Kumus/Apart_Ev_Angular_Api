package com.apart_ev.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apart_ev.dto.ApartDto;
import com.apart_ev.dto.BookAApartDto;
import com.apart_ev.dto.SearchApartDto;
import com.apart_ev.services.admin.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/apart")
    public ResponseEntity<?> postApart(@ModelAttribute ApartDto apartDto) throws IOException {
        boolean success = adminService.postApart(apartDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/aparts")
    public ResponseEntity<?> getAllAparts() {
        return ResponseEntity.ok(adminService.getAllAparts());
    }

    @DeleteMapping("/apart/{id}")
    public ResponseEntity<Void> deleteApart(@PathVariable Long id) {
        adminService.deleteApart(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/apart/{id}")
    public ResponseEntity<ApartDto> getApartById(@PathVariable Long id) {
        ApartDto apartDto = adminService.getApartById(id);
        return ResponseEntity.ok(apartDto);
    }

    @PutMapping(value = "/apart/{apartId}")
    public ResponseEntity<Void> updateApart(@PathVariable Long apartId, @ModelAttribute ApartDto apartDto)
            throws IOException {
        try {
            boolean success = adminService.updateApart(apartId, apartDto);
            if (success)
                return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/apart/bookings")
    public ResponseEntity<List<BookAApartDto>> getBookings() {
        return ResponseEntity.ok(adminService.getBookings());
    }

    @GetMapping("/apart/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status) {
        boolean success = adminService.changeBookingStatus(bookingId, status);
        if (success)
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/apart/search")
    public ResponseEntity<?> searchApart(@RequestBody SearchApartDto searchApartDto) {
        return ResponseEntity.ok(adminService.searchApart(searchApartDto));
    }

}
