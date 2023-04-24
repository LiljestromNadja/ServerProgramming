package fi.haagahelia.harkka;
/* Harkka v1.2 
 * 
 * Lisätty tuotteen ja tuoteluokan poistaminen
 * Lisätty index.html
 * Lisätty navigaatiolinkkejä .html -tiedostoihin
 * 
 * Lisätty tuotteen ja tuoteluokan luominen ja muokkaaminen, eli CRUD kasassa 
 * 
 * Muokattu Tuote.java sekä Tuoteluokka.java toString: iä
 * 
 * 
 * 
 * 
 * 
 * 
 *********************************************************************************************************
 * Harkka - "kehikko" (v1.1)
 * Lisätty entiteetit Tuote ja Tuoteluokka 
 * Lisätty One-to-Many -suhde, tuotteella voi olla yksi tuoteluokka, tuoteluokalla voi olla monta tuotetta
 * Lisätty ajonaikainen tietokanta
 * Ladattu Bootstrap 4.0 
 * 
 * 
 * Lisätty TuoteController, endpoint /tuotelista
 * Lisätty tuotelista.html 
 * 
 * */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.harkka.domain.Tuote;
import fi.haagahelia.harkka.domain.TuoteRepository;
import fi.haagahelia.harkka.domain.Tuoteluokka;
import fi.haagahelia.harkka.domain.TuoteluokkaRepository;

@SpringBootApplication
public class HarkkaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(HarkkaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HarkkaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(TuoteRepository tuoterepository, TuoteluokkaRepository tuoteluokkarepository) {
		return(args) ->{
			
			Tuoteluokka elektroniikka = new Tuoteluokka("Elektroniikka");
			Tuoteluokka isotkkoneet = new Tuoteluokka("Kodinkoneet");
			Tuoteluokka huonekalut = new Tuoteluokka("Huonekalut"); 
			
			tuoteluokkarepository.save(elektroniikka);
			tuoteluokkarepository.save(isotkkoneet);
			tuoteluokkarepository.save(huonekalut);
			
			log.info("LISÄTTY TIETOKANTAAN TUOTELUOKAT: " + elektroniikka +", " + isotkkoneet + ", " + huonekalut);
			
			
			Tuote tuote1 = new Tuote("Rannekello", "Helsinki", "Hieno digitaalinen rannekello", 25.00, tuoteluokkarepository.findByTuoteluokkanimi("Elektroniikka").get(0) );
			Tuote tuote2 = new Tuote("Kuulokkeet", "Helsinki", "Langattomat pelikuulokkeet mikrofonilla, OVH 120 €.", 40.00, tuoteluokkarepository.findByTuoteluokkanimi("Elektroniikka").get(0));
			Tuote tuote3 = new Tuote("Pyykinpesukone", "Vantaa", "Candy-merkkinen päältä täytettävä pyykinpesukone, 7kg rumpu.", 120, tuoteluokkarepository.findByTuoteluokkanimi("Kodinkoneet").get(0));
			Tuote tuote4 = new Tuote("Sohva", "Espoo", "3-istuttava harmaa sohva", 100, tuoteluokkarepository.findByTuoteluokkanimi("Huonekalut").get(0));
			
			
			tuoterepository.save(tuote1);
			tuoterepository.save(tuote2);
			tuoterepository.save(tuote3);
			tuoterepository.save(tuote4);
			log.info("LISÄTTY TIETOKANTAAN TUOTTEET ");
			log.info(tuote1.getTuotenimi());
			log.info(tuote2.getTuotenimi());
			log.info(tuote3.getTuotenimi());
			log.info(tuote4.getTuotenimi());
			
		};
		
	}

}
