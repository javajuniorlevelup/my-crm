package ru.levelup.mycrm.repo;

import org.springframework.data.repository.CrudRepository;
import ru.levelup.mycrm.model.User;

import java.util.Optional;

public interface UsersRepo extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
