package com.apart_ev.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apart_ev.dto.ApartDto;
import com.apart_ev.services.admin.AdminService;

import lombok.RequiredArgsConstructor;

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

}
