package com.apart_ev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apart_ev.entity.BookAApart;

@Repository
public interface BookAApartRepository extends JpaRepository<BookAApart, Long> {
    List<BookAApart> findAllByUserId(Long userId);
}
