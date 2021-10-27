package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void createNewBook() {
		Book book = new Book("Veljeni, Leijonamieli", "Astrid Lindgren", 1973, "234986-02348", 12.50, null);
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		Book book = new Book("Kirjan poisto", "Juho Kortelainen", 2021, "238444-22308947", 13.00, null);
		bookRepository.save(book);
		bookRepository.deleteById(book.getId());
		assertThat(book).isNull();
	}
	
	@Test
	public void searchBook() {
		Book book = new Book("Kirjan etsiminen", "Juho Kortelainen", 2021, "23747-0923573", 29.32, null);
		bookRepository.save(book);
		assertThat(bookRepository.findById(book.getId())).isNotNull();
	}
	
	

}
