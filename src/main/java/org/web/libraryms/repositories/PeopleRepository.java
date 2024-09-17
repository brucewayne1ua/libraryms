package org.web.libraryms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.libraryms.models.People;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Long> {
    @Override
    List<People> findAll();


    List<People> findBySurnameContainingIgnoreCase(String surname);
}
