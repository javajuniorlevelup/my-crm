package ru.levelup.mycrm.repo;

import org.springframework.data.repository.CrudRepository;
import ru.levelup.mycrm.model.Contact;

public interface ContactRepo extends CrudRepository<Contact, Long> {
}
