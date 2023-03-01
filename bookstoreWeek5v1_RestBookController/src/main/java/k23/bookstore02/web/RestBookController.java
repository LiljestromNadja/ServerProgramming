package k23.bookstore02.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import k23.bookstore02.domain.Book;
import k23.bookstore02.domain.BookRepository;

@RestController
public class RestBookController {
	
	private static final Logger log = LoggerFactory.getLogger(RestBookController.class);
	
	//injektoidaan Bookrepository RestBookController-luokalle
	@Autowired
	BookRepository bookRepository;
	
	//palautetaan lista kirjoista http://localhost:8080/booksjson
	@GetMapping("/booksjson")
	public Iterable<Book> getBooks() {
		log.info("nouda ja palauta lista kirjoista");
		return bookRepository.findAll();
	}
	
	//lisätään uusi kirja 
	@PostMapping("booksjson")
	Book newBook(@RequestBody Book newBook) {
		log.info("Lisätäään kirja " + newBook);
		return bookRepository.save(newBook);
	}
	
	// muokataan olemassa olevaa kirjaa
		@PutMapping("/booksjson/{id}")
		Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
			log.info("Muokataan kirjaa " + editedBook);
			editedBook.setId(id);
			return bookRepository.save(editedBook);
		}
	
	/*//delete book
	@DeleteMapping("/booksjson/{id}")
	void deleteBook(@PathVariable Long id) {
		bookRepository.deleteById(id);
	}*/
	
	//poistetaan kirja
	@DeleteMapping("/booksjson/{id}")
	public Iterable<Book> deleteBook(@PathVariable Long id) {
		log.info("Poistetaan kirja, id: " + id);
		bookRepository.deleteById(id);
		return bookRepository.findAll();
	}
	
	//etsitään ja palautetaan yksi kirja
	@GetMapping("/booksjson/{id}")
	Optional<Book> getBook(@PathVariable Long id) {
		log.info("Etsitään kirja, id: " + id);
		return bookRepository.findById(id);
		
	}
	


}
