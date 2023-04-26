/*Testattavat Tuoterepository ja Tuoteluokkarepository*/

package fi.haagahelia.harkka;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.haagahelia.harkka.domain.Tuote;
import fi.haagahelia.harkka.domain.TuoteRepository;
import fi.haagahelia.harkka.domain.Tuoteluokka;
import fi.haagahelia.harkka.domain.TuoteluokkaRepository;

@DataJpaTest
public class TuoteReposityTests {
	
	@Autowired
	TuoteRepository tuoterepository;
	
	@Autowired
	TuoteluokkaRepository tuoteluokkarepository;
	
	@Test
	public void findByTuotenimi() {
		List<Tuote> tuotteet = tuoterepository.findByTuotenimi("Rannekello");
		assertThat(tuotteet.get(0).getTuotenimi().equalsIgnoreCase("rannekello"));
	}
	
	@Test
	public void findTuoteluokkaByTuoteluokkanimi() {
		List<Tuoteluokka> tuoteluokat = tuoteluokkarepository.findByTuoteluokkanimi("Elektroniikka");
		assertThat(tuoteluokat.get(0).getTuoteluokkanimi().equalsIgnoreCase("elektroniikka"));
	}
	
	
 
	@Test
	public void findBySijainti() {
		List<Tuote> tuotteet = tuoterepository.findBySijainti("Helsinki"); //tietokannassa on kaksi tuotetta joiden sijainti on helsinki
		assertThat(tuotteet).hasSize(2);
	}
	
	@Test
	public void deleteByid() {
		
		tuoterepository.deleteById((long)1); //poistetaan tuote id 1, jossa sijainti helsinki. 
		List<Tuote> tuotteet = tuoterepository.findBySijainti("Helsinki"); //nyt jäljellä vain yksi tuote jossa sijainti helsinki
		
		assertThat(tuotteet).hasSize(1);
	}
	
	
	
	
	@Test
	public void lisaaTuote() {
		Tuote tuote = new Tuote();
		tuoterepository.save(tuote);
		assertNotEquals(tuote.getTuoteid(),null);
	}
	

		
		
	
	@Test
	public void muokkaaTuote() {
		Optional<Tuote> tuote = tuoterepository.findById((long)1);
		assertNotEquals(tuote.get().getTuoteid(),null); //varmistetaan että kyseisellä id:llä löytyy tuote
		tuote.get().setKuvaus("testi"); //muutetaan kuvaukseksi "testi"
		List<Tuote> tuotteet = tuoterepository.findByKuvaus("testi"); 
		assertThat(tuotteet).hasSize(1);
	}
	
	
	
}

