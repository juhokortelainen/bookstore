package hh.swd20.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// luodaan testidataa h2-tietokantaan
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository) {
		return (args) -> {
			log.info("add a couple of books");
			// tallennetaan tietokantaan kaksi kirjaa
			bookRepository.save(new Book("Veljeni, Leijonamieli", "Astrid Lindgren", 1973, "3849sd75834975", 12.50));
			bookRepository.save(new Book("Paahde", "Louis Sachar", 1998, "20387jhlk47534", 7.99));

			// tarkistetaan löytyykö tietokannasta lisätyt kirjat
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
