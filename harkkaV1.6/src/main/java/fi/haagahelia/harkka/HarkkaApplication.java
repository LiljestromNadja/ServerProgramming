package fi.haagahelia.harkka;
/***
 * Harkka v1.6
 * 
 * -JUnit
 * 
 * src/test/java > fi.haagahelia.harkka
 * -TuoteRepositoryTests.java
 * -RestinTesti.java 
 * 
 * ***********************************************************************************************
 * Harkka v1.5
 * 
 * Luotu package fi.haagahelia.service ja siirretty UserDetailServiceImpl.java sinne
 * 
 * Päivitetty WebSecurityConfig.javaa
 * 
 * Käyttäjien lisääminen
 * ApplicationUserRegistrationForm.java 
 * ApplicationUserRegistrationController -> käyttäjän lisääminen, poistaminen
 * 
 * registration.html -> käyttäjän lisääminen
 * userlist.html -> käyttäjien listaaminen, käyttäjän poistaminen
 * 
 * 
 * 
 * 
 * 
 * 
 *********************************************************************************************************
 * Harkka v1.4 - Spring Security 
 * 
 * Lisätty depedencyt spring-boot-starter-security, thymeleaf-extras-springsecurity6
 * 
 * Käytetään Spring securityn default login-sivua
 * 
 * Luotu WebSecurityConfig.java -> lisätty @EnableMethodSecurity(securedEnabled = true)
 * Luotu UserDetailServiceImpl.java
 * Luotu ApplicationUser.java
 * Luotu ApplicationUserRepository.java
 * 
 * Lisätty TuoteController.javaan oikeuksien tarkistus @PreAuthorize("hasAuthority('ADMIN')")
 * niin että vain admin -oikeuksilla voi lisätä/muokata/poistaa
 * 
 * Lisätty .html -tiedostoihin tieto, kuka kirjautunut, logout-nappi
 * 
 * Otettu tyyli käyttöön
 * 
 * 
 * 
 *********************************************************************************************************
 *
 * Harkka v1.3 - REST (Spring Data rest ja RestTuoteController)
 * 
 * Spring Data rest depedency ja polku, testattu että /api REST toimii, http://localhost:8080/api/tuotes jne.
 * 
 * Lisätty RestTuoteController.java (Tuote, Tuoteluokka REST), CRUD-toiminnot
 *
 * Testattu RestTuoteController Postmanilla
 * 
 * Päivitetty tuoteluokan poistaminen niin, ettei tuoteluokkaa pysty poistamaan jos siinä on tuotteita
 * 
 * 
 *********************************************************************************************************
 * Harkka v1.2 - CRUD
 * 
 * Lisätty tuotteen ja tuoteluokan poistaminen
 * Lisätty index.html
 * Lisätty navigaatiolinkkejä .html -tiedostoihin
 * 
 * Lisätty tuotteen ja tuoteluokan luominen ja muokkaaminen, eli CRUD kasassa 
 * 
 * Muokattu Tuote.java sekä Tuoteluokka.java toString: iä
 *  
 *********************************************************************************************************
 * Harkka - "kehikko" (v1.1) - Projektin luominen, Bootstrap
 * 
 * Lisätty entiteetit Tuote ja Tuoteluokka 
 * Lisätty One-to-Many -suhde, tuotteella voi olla yksi tuoteluokka, tuoteluokalla voi olla monta tuotetta
 * Lisätty ajonaikainen tietokanta JPA
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
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import fi.haagahelia.harkka.domain.ApplicationUser;
import fi.haagahelia.harkka.domain.ApplicationUserRepository;
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
	public CommandLineRunner demo(TuoteRepository tuoterepository, TuoteluokkaRepository tuoteluokkarepository, ApplicationUserRepository urepository) {
		return(args) ->{
			
			//Luodaan 
			Tuoteluokka elektroniikka = new Tuoteluokka("Elektroniikka");
			Tuoteluokka isotkkoneet = new Tuoteluokka("Kodinkoneet");
			Tuoteluokka huonekalut = new Tuoteluokka("Huonekalut"); 
			Tuoteluokka sisustus = new Tuoteluokka("Sisustus");
			
			//tallennetaan objektit tietokantaan
			tuoteluokkarepository.save(elektroniikka);
			tuoteluokkarepository.save(isotkkoneet);
			tuoteluokkarepository.save(huonekalut);
			tuoteluokkarepository.save(sisustus);
			
			log.info("LISÄTTY TIETOKANTAAN TUOTELUOKAT: " + elektroniikka +", " + isotkkoneet + ", " + huonekalut + ", " + sisustus);
			
			
			//Luodaan
			Tuote tuote1 = new Tuote("Rannekello", "Helsinki", "Hieno digitaalinen rannekello", 25.00, tuoteluokkarepository.findByTuoteluokkanimi("Elektroniikka").get(0) );
			Tuote tuote2 = new Tuote("Kuulokkeet", "Helsinki", "Langattomat pelikuulokkeet mikrofonilla, OVH 120 €.", 40.00, tuoteluokkarepository.findByTuoteluokkanimi("Elektroniikka").get(0));
			Tuote tuote3 = new Tuote("Pyykinpesukone", "Vantaa", "Candy-merkkinen päältä täytettävä pyykinpesukone, 7kg rumpu.", 120, tuoteluokkarepository.findByTuoteluokkanimi("Kodinkoneet").get(0));
			Tuote tuote4 = new Tuote("Sohva", "Espoo", "3-istuttava harmaa sohva", 100, tuoteluokkarepository.findByTuoteluokkanimi("Huonekalut").get(0));
			
			//tallennetaan tietokantaan
			tuoterepository.save(tuote1);
			tuoterepository.save(tuote2);
			tuoterepository.save(tuote3);
			tuoterepository.save(tuote4);
			
			log.info("LISÄTTY TIETOKANTAAN TUOTTEET ");
			log.info(tuote1.getTuotenimi());
			log.info(tuote2.getTuotenimi());
			log.info(tuote3.getTuotenimi());
			log.info(tuote4.getTuotenimi());
			
			//v1.4
			//Luodaan ja lisätään käyttäjät tietokantaan, admin/admin ja user/user, huolto/huolto
			ApplicationUser user1 = new ApplicationUser("user","$2a$10$RIqlxElPXzQKJayHKJwSNOxDMnMh.j.OHwQvOoPj0gld.sbXsqqgK" ,"USER");
			ApplicationUser user2 = new ApplicationUser("admin", "$2a$10$aGjp6jEUEspwUkQrCbGAWuKScc9DRHTQ6LXMRX2TAM5A6tzHdy8/6", "ADMIN");
			ApplicationUser user3 = new ApplicationUser("huolto", "$2a$10$lDtQP3VTBBHPocsCga.a6.iqXrQq2S3.nlaWJieniRwYlOUjpttUS", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			log.info("Lisätty käyttäjät admin, user, huolto");
		};
		
	}

}
