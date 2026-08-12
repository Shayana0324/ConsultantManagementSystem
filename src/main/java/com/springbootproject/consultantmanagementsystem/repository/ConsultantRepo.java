/*
* This is a data access layer
* Extending JpaRepository
* We can write any SQL or JDBC code for basic CRUB
* */
package com.springbootproject.consultantmanagementsystem.repository;

import com.springbootproject.consultantmanagementsystem.entity.Consultant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultantRepo extends JpaRepository<Consultant, Long> {
    Page<Consultant> findByNameContainingIgnoreCaseOrTechnologyContainingIgnoreCase(String name, String technology, Pageable pageable);

    long countByStatus(String status);
}
