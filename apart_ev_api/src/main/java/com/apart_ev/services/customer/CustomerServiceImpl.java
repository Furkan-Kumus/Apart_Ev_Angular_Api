package com.apart_ev.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.apart_ev.dto.ApartDto;
import com.apart_ev.dto.BookAApartDto;
import com.apart_ev.entity.Apart;
import com.apart_ev.entity.BookAApart;
import com.apart_ev.entity.User;
import com.apart_ev.enums.BookApartStatus;
import com.apart_ev.repository.ApartRepository;
import com.apart_ev.repository.BookAApartRepository;
import com.apart_ev.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final ApartRepository apartRepository;
    private final UserRepository userRepository;
    private final BookAApartRepository bookAApartRepository;

    @Override
    public List<ApartDto> getAllAparts() {
        return apartRepository.findAll().stream().map(Apart::geApartDto).collect(Collectors.toList());
    }

    @Override
    public boolean BookAApart(BookAApartDto bookAApartDto) {
        Optional<Apart> optionalApart = apartRepository.findById(bookAApartDto.getApartId());
        Optional<User> optionalUser = userRepository.findById(bookAApartDto.getUserId());
        if (optionalApart.isPresent() && optionalUser.isPresent()) {
            Apart existingApart = optionalApart.get();
            BookAApart bookAApart = new BookAApart();
            bookAApart.setUser(optionalUser.get());
            bookAApart.setApart(existingApart);
            bookAApart.setBookApartStatus(BookApartStatus.PENDING);
            long diffInMilliSeconds = bookAApartDto.getToDate().getTime() - bookAApartDto.getFromDate().getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);
            bookAApart.setDays(days);
            bookAApart.setPrice(existingApart.getPrice() * days);
            bookAApartRepository.save(bookAApart);
            return true;
        }
        return false;

    }

    @Override
    public ApartDto getApartById(Long apartId) {
        Optional<Apart> optionalApart = apartRepository.findById(apartId);
        return optionalApart.map(Apart::geApartDto).orElse(null);
    }

    @Override
    public List<BookAApartDto> getBookingsByUserId(Long userId) {
        return bookAApartRepository.findAllByUserId(userId).stream().map(BookAApart::getBookAApartDto)
                .collect(Collectors.toList());
    }
}
