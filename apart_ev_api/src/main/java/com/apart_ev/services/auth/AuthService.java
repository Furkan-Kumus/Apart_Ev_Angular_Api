package com.apart_ev.services.auth;

import com.apart_ev.dto.SignupRequest;
import com.apart_ev.dto.UserDto;

public interface AuthService {
    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
