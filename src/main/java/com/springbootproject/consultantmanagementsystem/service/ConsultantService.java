package com.springbootproject.consultantmanagementsystem.service;

import com.springbootproject.consultantmanagementsystem.entity.Consultant;
import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ConsultantService {
    Consultant save(Consultant consultant);

    Optional<Consultant> findById(Long id);

    List<Consultant> findAll();

    Page<Consultant> search(String keyword, Pageable pageable);

    void deleteById(Long id);

    long countAll();

    long countActive();

    long countInactive();
}
