package com.apart_ev.services.admin;

import org.springframework.stereotype.Service;

import com.apart_ev.dto.ApartDto;
import com.apart_ev.entity.Apart;
import com.apart_ev.repository.ApartRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    @Override
    public List<ApartDto> getAllAparts() {
        return apartRepository.findAll().stream().map(Apart::geApartDto).collect(Collectors.toList());
    }

    @Override
    public void deleteApart(Long id) {
        apartRepository.deleteById(id);
    }

    @Override
    public ApartDto getApartById(Long id) {
        Optional<Apart> optionalApart = apartRepository.findById(id);
        return optionalApart.map(Apart::geApartDto).orElse(null);
    }

    @Override
    public boolean updateApart(Long apartId, ApartDto apartDto) throws IOException {
        Optional<Apart> optionalApart = apartRepository.findById(apartId);
        if (optionalApart.isPresent()) {
            Apart existingApart = optionalApart.get();
            if (apartDto.getImage() != null) {
                existingApart.setImage(apartDto.getImage().getBytes());
            }
            existingApart.setPrice(apartDto.getPrice());
            existingApart.setYear(apartDto.getYear());
            existingApart.setType(apartDto.getType());
            existingApart.setDescription(apartDto.getDescription());
            existingApart.setTransmission(apartDto.getTransmission());
            existingApart.setColor(apartDto.getColor());
            existingApart.setName(apartDto.getName());
            existingApart.setBrand(apartDto.getBrand());
            apartRepository.save(existingApart);
            return true;
        } else {
            return false;
        }

    }

}
