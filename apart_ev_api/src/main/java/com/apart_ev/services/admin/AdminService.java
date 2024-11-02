package com.apart_ev.services.admin;

import java.io.IOException;

import com.apart_ev.dto.ApartDto;

public interface AdminService {
    boolean postApart(ApartDto apartDto) throws IOException;
}
