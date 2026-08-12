package com.springbootproject.consultantmanagementsystem.service;

import com.springbootproject.consultantmanagementsystem.entity.Consultant;
import com.springbootproject.consultantmanagementsystem.repository.ConsultantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultantServiceImpl implements ConsultantService {

    private final ConsultantRepo consultantRepository;

    // Constructor injection -- Spring automatically supplies the repository
    // bean here at startup. This is the recommended way to wire dependencies.
    @Autowired
    public ConsultantServiceImpl(ConsultantRepo consultantRepository) {
        this.consultantRepository = consultantRepository;
    }

    @Override
    public Consultant save(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    @Override
    public Optional<Consultant> findById(Long id) {
        return consultantRepository.findById(id);
    }

    @Override
    public List<Consultant> findAll() {
        return consultantRepository.findAll();
    }

    @Override
    public Page<Consultant> search(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isBlank()) {
            return consultantRepository.findAll(pageable);
        }
        return consultantRepository
                .findByNameContainingIgnoreCaseOrTechnologyContainingIgnoreCase(keyword, keyword, pageable);
    }

    @Override
    public void deleteById(Long id) {
        consultantRepository.deleteById(id);
    }

    @Override
    public long countAll() {
        return consultantRepository.count();
    }

    @Override
    public long countActive() {
        return consultantRepository.countByStatus("Active");
    }

    @Override
    public long countInactive() {
        return consultantRepository.countByStatus("Inactive");
    }
}

