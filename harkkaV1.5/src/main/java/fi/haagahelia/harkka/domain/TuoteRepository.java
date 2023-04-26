//interface, CRUD-toiminnallisuudet
package fi.haagahelia.harkka.domain;

import org.springframework.data.repository.CrudRepository;

public interface TuoteRepository extends CrudRepository<Tuote, Long> {
	
	
	
	
	
	//voi tehdä tietokantaan hakuja eri attribuuteilla, esim findById(Long id), findByTuotenimi(String tuotenimi), findBySijainti(String sijainti))
	
	
	

}
