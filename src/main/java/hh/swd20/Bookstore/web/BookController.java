package hh.swd20.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		Book book = repository.findById(id).orElse(null);
		model.addAttribute(book); // tai model.addAttribute("book", repository.dinfById(id).get());
		return "editbook";
	}

}
