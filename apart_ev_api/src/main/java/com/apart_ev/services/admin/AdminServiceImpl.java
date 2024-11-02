package com.apart_ev.services.admin;

import org.springframework.stereotype.Service;

import com.apart_ev.dto.ApartDto;
import com.apart_ev.entity.Apart;
import com.apart_ev.repository.ApartRepository;

import java.io.IOException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ApartRepository apartRepository;

    @Override
    public boolean postApart(ApartDto apartDto) throws IOException {
        try {
            Apart apart = new Apart();
            apart.setName(apartDto.getName());
            apart.setBrand(apartDto.getBrand());
            apart.setColor(apartDto.getColor());
            apart.setPrice(apartDto.getPrice());
            apart.setYear(apartDto.getYear());
            apart.setType(apartDto.getType());
            apart.setDescription(apartDto.getDescription());
            apart.setTransmission(apartDto.getTransmission());
            apart.setImage(apartDto.getImage().getBytes());
            apartRepository.save(apart);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
