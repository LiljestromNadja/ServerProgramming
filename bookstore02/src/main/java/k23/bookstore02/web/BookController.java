package k23.bookstore02.web;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.Logger;
import k23.bookstore02.domain.Book;
import k23.bookstore02.domain.BookRepository;

@Controller
public class BookController {
	//private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@RequestMapping(value= {"/main"})
	public String showMainPage() {
		return "index";  //index.html
	}
	@Autowired
	private BookRepository repository;
	
	@RequestMapping(value = {"/","/booklist"})
	public String bookList(Model model) {
		
		model.addAttribute("books", repository.findAll());
		
		return "booklist.html";	
	}
	
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
}
