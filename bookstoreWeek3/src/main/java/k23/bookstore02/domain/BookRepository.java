package k23.bookstore02.domain;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

	//List<Book> findByTitle(String title); Tulevaisuutta varten :) Muista ottaa importointi pois piilosta
}
