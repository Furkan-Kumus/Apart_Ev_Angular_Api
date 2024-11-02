package com.apart_ev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apart_ev.entity.Apart;

@Repository
public interface ApartRepository extends JpaRepository<Apart, Long> {

}
