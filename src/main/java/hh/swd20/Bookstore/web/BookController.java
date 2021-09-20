package hh.swd20.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	@Autowired /*
				 * Kun Spring-sovellus käynnistyy, se luo itse BookRepository-rajapinnan
				 * toteuttavan luokan olion ja kytkee sen tämän Controlleriluokan olion
				 * attribuutti-olioksi
				 */
	private BookRepository repository;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getGreeting() {
		return "greeting";
	}

	// haetaan tietokannasta kaikki kirjat
	@RequestMapping(value = "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist"; // booklist.html
	}

}
