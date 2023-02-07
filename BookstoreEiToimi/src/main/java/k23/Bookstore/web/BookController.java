package k23.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import k23.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	@GetMapping("/index") 
	public String handleGet() {
		return "";
	}
	
	@Autowired
	private BookRepository repository;
	
	@RequestMapping("/booklist") 
	public String booklist(Model model) {
		model.addAttribute("books", repository.findAll()); // (avain,arvo) eli tässä tapauksessa lista kaikista kirjoista
		return "booklist"; //templaten nimi
	}

}
