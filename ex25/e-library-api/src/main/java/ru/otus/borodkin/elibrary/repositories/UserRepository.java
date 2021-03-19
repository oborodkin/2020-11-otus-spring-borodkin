package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.borodkin.elibrary.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
