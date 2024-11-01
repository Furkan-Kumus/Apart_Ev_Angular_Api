package com.apart_ev.dto;

import com.apart_ev.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;

}
