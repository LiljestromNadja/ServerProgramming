package k23.bookstore02.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import k23.bookstore02.domain.Book;
import k23.bookstore02.domain.BookRepository;
import k23.bookstore02.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	//Main/index
	@RequestMapping(value= {"/main" , "/index"}) //Endpointit
	public String showMainPage() {
		return "index";  //index.html
	}
	
	//Kaikki kirjat	
	@RequestMapping(value = {"/","/booklist"}) //endpoint: http://localhost:8080/ ja  http://localhost:8080/booklist
	public String bookList(Model model) {
		
		model.addAttribute("books", repository.findAll());
		
		return "booklist.html";	
	}
	

	//Lisätään kirja
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	//Tallennetaan uusi kirja
	@RequestMapping(value="/save", method = RequestMethod.POST) //tämä käytössä addbook.html :ssä
	public String save(@Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some validation error happened");
			model.addAttribute("book", book);
			model.addAttribute("categories", crepository.findAll()); // <---- tämän takia edellinen kaatui, tärkeä
			return "addbook";
		}
		repository.save(book);
		return "redirect:/booklist";
	}

	//Poista kirja
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	

	//Muokkaa
	@RequestMapping(value= "/editBook/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", repository.findById(id)); //bookID
		model.addAttribute("categories", crepository.findAll());
		return "editBook";
	}
/*
	@PostMapping("saveBook") //tämä käytössä editbook.html:ssä
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}*/
}
