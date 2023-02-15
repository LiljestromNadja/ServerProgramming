package k23.bookstore02;
//VAIHDETTU SB versioon 3.0.2 kun näitä tehtäviä tehdessä versiolla 2.7.8. tuli jotain ihme herjoja pom.xml:stä

//Category added

//Opettajalle kysymys, toStringin takia tulee virheilmoitus. (Book.java)

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k23.bookstore02.domain.Book;
import k23.bookstore02.domain.BookRepository;
import k23.bookstore02.domain.Category;
import k23.bookstore02.domain.CategoryRepository;

@SpringBootApplication
public class Bookstore02Application {
	
	private static final Logger log = LoggerFactory.getLogger(Bookstore02Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Bookstore02Application.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository) {
		return(args) ->{
			
			Category ykkonen =new Category("IT");
			Category kakkonen=new Category("Ohjelmointi");
			
			crepository.save(ykkonen);
			crepository.save(kakkonen);
			
			Book kirjayks = new Book("Ohjelmoinnin salat", "tuntematon", "978-951-12345-0-1" , 2010, 24.95, crepository.findByName("IT").get(0));
			Book kirjakaks = new Book("Tietokannat", "Severi Hakkeri", "978-951-12345-0-2", 2018, 29.90, crepository.findByName("Ohjelmointi").get(0));
			Book kirjakolme = new Book("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "978-951-12345-0-3", 2020, 24.95, crepository.findByName("Ohjelmointi").get(0));
			//Book kirjakolme = new Book("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "978-951-12345-0-3", 2020, 24.95, kakkonen);
			
			
			//Tallentaa objektin tietokantaan
			System.out.println("sinne meni!");
			repository.save(kirjayks);
			repository.save(kirjakaks);
			repository.save(kirjakolme);
			
			log.info("lisätty kirjat 1,2,3");
			
			System.out.println("nouda, fetch");
			for (Book book : repository.findAll()) {
			System.out.println(book.toString());
			}
		};
	}

}
