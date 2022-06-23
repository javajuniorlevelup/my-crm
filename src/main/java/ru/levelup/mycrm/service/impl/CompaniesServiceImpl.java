package ru.levelup.mycrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import ru.levelup.mycrm.model.Company;
import ru.levelup.mycrm.repo.CompaniesRepo;
import ru.levelup.mycrm.service.CompaniesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompaniesServiceImpl implements CompaniesService {

    private final CompaniesRepo companiesRepo;

    @Override
    public List<Company> findAll() {
        return Streamable.of(companiesRepo.findAll()).toList();
    }
}
