package hh.swd20.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired /*
				 * Kun Spring-sovellus käynnistyy, se luo itse BookRepository-rajapinnan
				 * toteuttavan luokan olion ja kytkee sen tämän Controlleriluokan olion
				 * attribuutti-olioksi
				 */
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository categoryrepository;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getGreeting() {
		return "greeting";
	}

	// haetaan tietokannasta kaikki kirjat
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist"; // booklist.html
	}
	
	// RESTful service to get all books
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	
	// RESTful servie to get book by id
	@RequestMapping(value ="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return repository.findById(id);
	}

	// tyhjän lisäyslomakkeen palautus
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryrepository.findAll());
		return "addbook";
	}

	// tallennetaan kirja
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	// poistetaan kirja id:n perusteella
	@PreAuthorize(value = "hasRole('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../booklist";
	}


	@PreAuthorize(value = "hasRole('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		Book book = repository.findById(id).orElse(null);
		model.addAttribute(book); // tai model.addAttribute("book", repository.dinfById(id).get());
		return "editbook";
	}

}
