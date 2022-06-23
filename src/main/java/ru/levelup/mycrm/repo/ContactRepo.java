package ru.levelup.mycrm.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.levelup.mycrm.model.Contact;

import java.util.List;

public interface ContactRepo extends CrudRepository<Contact, Long> {

    @Query("select c from Contact c where c.firstName like %:q% or c.lastName like %:q%")
    List<Contact> findByQuery(@Param("q") String query);

}
