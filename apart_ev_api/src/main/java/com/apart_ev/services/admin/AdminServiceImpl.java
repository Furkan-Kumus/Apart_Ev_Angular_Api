package com.apart_ev.services.admin;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.apart_ev.dto.ApartDto;
import com.apart_ev.dto.ApartDtoListDto;
import com.apart_ev.dto.BookAApartDto;
import com.apart_ev.dto.SearchApartDto;
import com.apart_ev.entity.Apart;
import com.apart_ev.entity.BookAApart;
import com.apart_ev.enums.BookApartStatus;
import com.apart_ev.repository.ApartRepository;
import com.apart_ev.repository.BookAApartRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.IOException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ApartRepository apartRepository;
    private final BookAApartRepository bookAApartRepository;

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

    @Override
    public List<BookAApartDto> getBookings() {
        return bookAApartRepository.findAll().stream().map(BookAApart::getBookAApartDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        Optional<BookAApart> optionalBookAApart = bookAApartRepository.findById(bookingId);
        if (optionalBookAApart.isPresent()) {
            BookAApart existingBookAApart = optionalBookAApart.get();
            if (Objects.equals(status, "Approve"))
                existingBookAApart.setBookApartStatus((BookApartStatus.APPROVED));
            else
                existingBookAApart.setBookApartStatus(BookApartStatus.REJECTED);
            bookAApartRepository.save(existingBookAApart);
            return true;
        }
        return false;
    }

    @Override
    public ApartDtoListDto searchApart(SearchApartDto searchApartDto) {
        Apart apart = new Apart();
        apart.setBrand(searchApartDto.getBrand());
        apart.setType(searchApartDto.getType());
        apart.setTransmission(searchApartDto.getTransmission());
        apart.setColor(searchApartDto.getColor());

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Apart> apartExample = Example.of(apart, exampleMatcher);
        List<Apart> apartList = apartRepository.findAll(apartExample);

        ApartDtoListDto apartDtoListDto = new ApartDtoListDto();
        apartDtoListDto.setApartDtoList(apartList.stream().map(Apart::geApartDto).collect(Collectors.toList()));

        return apartDtoListDto;

    }

}
