package de.yahya.bibliothek;


import de.yahya.bibliothek.model.Book;
import de.yahya.bibliothek.model.Status;
import de.yahya.bibliothek.model.Genre;
import de.yahya.bibliothek.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BibliothekApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(BibliothekApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		/*
		Book.BookBuilder builder = Book.builder();
		builder.genre(Genre.CHILD).author("Mary Pope Osborne").status(Status.AVAILABLE).loanDate(null);

		List<Book> books = new ArrayList<>();
		Book b = new Book();
		b.setGenre(Genre.NOVEL);
		b.setAuthor("Karin Fossum");
		b.setTitle("Fremde Blicke");
		b.setStatus(Status.ONLOAN);
		b.setLoanDate(LocalDate.of(2023,02,20));
		books.add(b);

		books.add(builder.title("Das magische Baumhaus").build());
		books.add(builder.title("Das Geheimnis der Mumie").build());
		books.add(builder.title("Zauberreise in verwunschene Welten").build());

		bookRepository.saveAll(books);
		*/
	}
}

