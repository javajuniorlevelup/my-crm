package ru.levelup.mycrm.repo;

import org.springframework.data.repository.CrudRepository;
import ru.levelup.mycrm.model.Company;

public interface CompaniesRepo extends CrudRepository<Company, Long> {
}
