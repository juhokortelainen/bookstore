package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;
import hh.swd20.Bookstore.domain.User;
import hh.swd20.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// luodaan testidataa h2-tietokantaan
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryrepository, UserRepository userrepository) {
		return (args) -> {
			// luodaan muutama kategoria
			log.info("Save some sample categories");
			Category category1 = new Category("Fantasy");
			categoryrepository.save(category1);
			Category category2 = new Category("Action");
			categoryrepository.save(category2);
			Category category3 = new Category("Horror");
			categoryrepository.save(category3);

			// logitetaan tietokannan kategoriat
			log.info("Fetch all categories");
			for (Category category : categoryrepository.findAll()) {
				log.info(category.toString());
			}

			log.info("add a couple of books");
			// tallennetaan tietokantaan kaksi kirjaa
			bookRepository.save(new Book("Veljeni, Leijonamieli", "Astrid Lindgren", 1973, "3849sd75834975", 12.50, category1));
			bookRepository.save(new Book("Paahde", "Louis Sachar", 1998, "20387jhlk47534", 7.99, category2));
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$10$geTp01pc6ODcXUS8Ec6Ge.PE7gvo0e21dR2qD0C9JDjE8UVxMCFN2", "user@user.mail", "USER");
			User user2 = new User("admin", "$2a$10$ueTh8oXVr4Nof1kXwwMsreVPz1t556W7AXlAMPhB76AbZybnxD2nG", "admin@admin.mail", "ADMIN");
			userrepository.save(user1);
			userrepository.save(user2);
			

			// tarkistetaan löytyykö tietokannasta lisätyt kirjat
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
