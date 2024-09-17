package org.web.libraryms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.libraryms.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Дополнительные методы, если нужно
}
