//Tuote ja Tuoteluokka REST Controller
/*Rest-rajapinta hyödynnetään jos esim. toinen sovellus haluaa käyttää 
tietokannan tietoja, esim hakea tiettyjä/kaikkia tuotteita
Toimintalogiikaltaan samankaltainen kuin esim. TuoteController, 
RestController palauttaa tiedon Thymeleaf/html -sivun sijaan 
JSON -muodossa.
Muista JsonIgnore (Tuoteluokka.java), muuten tulee loop jossa haetaan
Tuote.tuoteluokka -> Tuoteluokka viite tuotteeseen -> Tuote.tuoteluokka jne..
 */
package fi.haagahelia.harkka.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.harkka.domain.Tuote;
import fi.haagahelia.harkka.domain.TuoteRepository;
import fi.haagahelia.harkka.domain.Tuoteluokka;
import fi.haagahelia.harkka.domain.TuoteluokkaRepository;


@RestController //palauttaa jsonia
public class RestTuoteController {

	// injektoidaan TuoteRepository ja TuoteluokkaRepository RestController-luokkaan
	@Autowired
	private TuoteRepository tuoterepository;

	@Autowired
	private TuoteluokkaRepository tuoteluokkarepository;

	// Tuote REST
	// Palautetaan JSON (ei Thymeleaf/html kuten TuoteControllerissa)

	// palautetaan lista tuotteista http://localhost:8080/tuotteetrestjson
	@GetMapping("/tuotteetrestjson")
	public Iterable<Tuote> getTuotteet() {
		System.out.println("nouda ja palauta lista tuotteista JSON-muodossa");

		return tuoterepository.findAll();
	}

	// lisätään uusi tuote http://localhost:8080/uusituoterestjson
	@PostMapping("uusituoterestjson")
	Tuote uusiTuote(@RequestBody Tuote uusiTuote) {

		System.out.println("Lisätään tuote" + uusiTuote);

		return tuoterepository.save(uusiTuote);
	}

	// muokataan olemassa olevaa tuotetta http://localhost:8080/tuotteetrestjson/id
	@PutMapping("/tuotteetrestjson/{id}")
	Tuote muokkaaTuote(@RequestBody Tuote muokattuTuote, @PathVariable Long id) {

		System.out.println("Muokataan tuotetta" + muokattuTuote);
		muokattuTuote.setTuoteid(id);
		return tuoterepository.save(muokattuTuote);
	}

	// poistetaan tuote http://localhost:8080/tuotteetrestjson/id
	@DeleteMapping("/tuotteetrestjson/{id}")
	public Iterable<Tuote> poistaTuote(@PathVariable Long id) {
		System.out.println("Poistetaan tuote, id " + id);

		tuoterepository.deleteById(id);
		return tuoterepository.findAll();
	}

	// etsitään ja palautetaan yksi tuote http://localhost:8080/tuotteetrestjson/id
	@GetMapping("/tuotteetrestjson/{id}")
	Optional<Tuote> getTuote(@PathVariable Long id) {
		System.out.println("Etsitään tuote, id: " + id);

		return tuoterepository.findById(id);

	}

	// Tuoteluokka REST
	// Palautetaan JSON

	// palautetaan lista tuoteluokista http://localhost:8080/tuoteluokatrestjson
	@GetMapping("/tuoteluokatrestjson")
	public @ResponseBody List<Tuoteluokka> getTuoteluokat() {
		System.out.println("nouda ja palauta lista tuoteluokista JSON-muodossa");

		return (List<Tuoteluokka>) tuoteluokkarepository.findAll();
	}

	// lisätään uusi tuoteluokka http://localhost:8080/uusituoteluokkarestjson
	@PostMapping("uusituoteluokkarestjson")
	Tuoteluokka uusiTuoteluokka(@RequestBody Tuoteluokka uusiTuoteluokka) {

		System.out.println("Lisätään tuoteluokka " + uusiTuoteluokka);

		return tuoteluokkarepository.save(uusiTuoteluokka);
	}

	// muokataan olemassa olevaa tuoteluokkaa
	// http://localhost:8080/tuoteluokatrestjson/id
	@PutMapping("/tuoteluokatrestjson/{id}")
	Tuoteluokka muokkaaTuoteluokka(@RequestBody Tuoteluokka muokattuTuoteluokka, @PathVariable Long id) {

		System.out.println("Muokataan tuoteluokkaa " + muokattuTuoteluokka);
		muokattuTuoteluokka.setTuoteluokkaid(id);
		return tuoteluokkarepository.save(muokattuTuoteluokka);
	}

	// poistetaan tuoteluokka http://localhost:8080/tuoteluokatrestjson/id
	@DeleteMapping("/tuoteluokatrestjson/{id}")
	public List<Tuoteluokka> poistaTuoteluokka(@PathVariable Long id) {
		System.out.println("Poistetaan tuoteluokka, id " + id);

		if (tuoteluokkarepository.findById(id).get().getTuotteet().isEmpty()) {
			tuoteluokkarepository.deleteById(id);
		} else {
			System.out.println("Tuoteluokkaa ei voi poistaa, koska siinä on tuotteita");
		}

		return (List<Tuoteluokka>) tuoteluokkarepository.findAll();
	}
	
	// etsitään ja palautetaan yksi tuoteluokka id:n perusteella http://localhost:8080/tuoteluokatrestjson/{id}
	@GetMapping("tuoteluokatrestjson/{id}")
	Optional<Tuoteluokka> getTuoteluokka(@PathVariable Long id) {
		System.out.println("Etsitään tuoteluokka, id: " + id);

		return tuoteluokkarepository.findById(id);

	}

}
