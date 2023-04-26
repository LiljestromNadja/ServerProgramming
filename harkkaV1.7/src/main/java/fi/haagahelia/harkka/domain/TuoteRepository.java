//interface, CRUD-toiminnallisuudet
package fi.haagahelia.harkka.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TuoteRepository extends CrudRepository<Tuote, Long> {
	
	List<Tuote> findByTuotenimi(String tuotenimi);
	List<Tuote> findBySijainti(String sijainti);
	List<Tuote> findByKuvaus(String kuvaus);

	
	
	//voi tehdä tietokantaan hakuja eri attribuuteilla, esim findById(Long id), findByTuotenimi(String tuotenimi), findBySijainti(String sijainti))
	
	
	

}
