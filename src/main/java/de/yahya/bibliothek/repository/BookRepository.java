package de.yahya.bibliothek.repository;

import de.yahya.bibliothek.model.Book;
import de.yahya.bibliothek.model.Genre;
import de.yahya.bibliothek.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

//    List<Book> findByGenreIsNot(Genre genre);

    Optional<Book> findById(Long id);

    List<Book> findByTitleContainsIgnoreCase(String title);

    List<Book> findByAuthorContainsIgnoreCase(String author);

    List<Book> findALLByGenre(Genre genre);


    List<Book> findByStatus(Status available);

}