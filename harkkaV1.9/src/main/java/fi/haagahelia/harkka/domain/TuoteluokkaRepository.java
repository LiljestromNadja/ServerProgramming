//CRUD -toiminnallisuudet 
package fi.haagahelia.harkka.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TuoteluokkaRepository extends CrudRepository<Tuoteluokka, Long> {
	
	List<Tuoteluokka> findByTuoteluokkanimi(String tuoteluokkanimi);

}
