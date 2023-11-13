package de.yahya.bibliothek.controller;

import de.yahya.bibliothek.exception.NotAvailableException;
import de.yahya.bibliothek.model.Book;
import de.yahya.bibliothek.model.Genre;
import de.yahya.bibliothek.model.Status;
import de.yahya.bibliothek.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {

    /*
    CRUD
    POST = Create
    GET = Read (all oder one)
    PUT = Update
    DELETE = Delete
    */

    @Autowired
    private BookRepository bookRepository;

    //@RequestMapping(value = "", method = RequestMethod.GET)
    @GetMapping("")
    public List<Book> index() {
        return bookRepository.findAll();
//        return bookRepository.findByGenreIsNot(Genre.CHILD);
    }

    @GetMapping("find/{id}")
    public Optional<Book> byId(@PathVariable Long id) {
          return bookRepository.findById(id);
    }
    @GetMapping("find/title/{title}")
    public  List<Book> byTitle(@PathVariable("title") String title) {
        return bookRepository.findByTitleContainsIgnoreCase(title);
    }
    @GetMapping("find/author/{author}")
    public  List<Book> byAuthor(@PathVariable("author") String author) {
        return bookRepository.findByAuthorContainsIgnoreCase(author);
    }
    @GetMapping("find/genre/{genre}")
    public  List<Book> byGenre(@PathVariable("genre") Genre genre) {
        return bookRepository.findALLByGenre(genre);
    }

    @PostMapping("") // Speichern (INSERT)
    public Book insert(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("{id}") // Speichern (UPDATE)
    public Book update(@PathVariable("id") Long id, @RequestBody Book book) {
        Optional<Book> opt = bookRepository.findById(id);
        if(opt.isPresent()) {
            Book b = opt.get();
            b.setGenre(book.getGenre());
            b.setAuthor(book.getAuthor());
            b.setTitle(book.getTitle());
            b.setStatus(book.getStatus());
            return bookRepository.save(b);
        }
        return new Book();
    }

    @DeleteMapping("{id}") // Löschen
    public void delete(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
    }

    @GetMapping("{loan}")
    public List<Book> getAvailable() {
        return bookRepository.findByStatus(Status.AVAILABLE);
    }

    @PostMapping("loan/{id}")
    public Book loan(@PathVariable Long id) {
        Book book = bookRepository.findById(id).get();
        if (book.getStatus().equals(Status.AVAILABLE)) {
            var opt = bookRepository.findById(id);
            if (opt.isPresent()) {
                var dbBook = opt.get();
                dbBook.setStatus(Status.ONLOAN);
                dbBook.setLoanDate(LocalDate.now());
                return bookRepository.save(dbBook);
            }
        }
        throw new NotAvailableException();
    }

    @PostMapping("back/{id}")
    public Book back(@PathVariable Long id) {
        Book book = bookRepository.findById(id).get();
        if (book.getStatus().equals(Status.ONLOAN)) {
            var opt = bookRepository.findById(id);
            if (opt.isPresent()) {
                var dbBook = opt.get();
                dbBook.setStatus(Status.AVAILABLE);
                dbBook.setLoanDate(null);
                return bookRepository.save(dbBook);
            }
        }
        return book;
    }
}