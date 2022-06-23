package ru.levelup.mycrm.service;

import ru.levelup.mycrm.model.dto.ContactDto;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Optional<ContactDto> findById(Long id);
    List<ContactDto> findAll();
    Long save(ContactDto contactDto);

    List<ContactDto> findByQuery(String query);
}
