//Controller, sovelluksen endpointit
//Tässä Controllerissa Tuote ja Tuoteluokka
package fi.haagahelia.harkka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.harkka.domain.Tuote;
import fi.haagahelia.harkka.domain.TuoteRepository;
import fi.haagahelia.harkka.domain.Tuoteluokka;
import fi.haagahelia.harkka.domain.TuoteluokkaRepository;
import jakarta.validation.Valid;

@Controller // palauttaa html-sivun
public class TuoteController {

	// injektoidaan TuoteRepository ja TuoteluokkaRepository Controller-luokkaan
	@Autowired
	private TuoteRepository tuoterepository;

	@Autowired
	private TuoteluokkaRepository tuoteluokkarepository;

	// index
	@GetMapping(value = { "/", "index" }) // localhost:8080 localhost:8080/index
	public String showMainPage() {
		System.out.println("Näytetään index.html");
		return "index"; // index.html

	}

	// TUOTTEET: hae kaikki, poista, lisää, muokkaa

	// Haetaan ja listataan kaikki tuotteet, sekä userilla että adminilla oikeus
	@RequestMapping("/tuotelista")
	public String tuotelista(Model model) {
		model.addAttribute("tuotteet", tuoterepository.findAll());// haetaan tietokannasta näytettäväksi ("nimi", arvo)
		System.out.println("Näytetään tuotelista");
		return "tuotelista"; // templaten nimi, .html, view --> src/main/resources/templates

	}

	// Poistetaan tuote, oikeus vain adminilla
	@PreAuthorize("hasAuthority('ADMIN')") // metoditason tarkistus onko oikeus poistaa
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTuote(@PathVariable("id") Long tuoteid, Model model) {
		tuoterepository.deleteById(tuoteid);
		System.out.println("poistettu tuote, id: " + tuoteid);
		return "redirect:../tuotelista";
	}

	// Lisätään tuote, oikeus vain adminilla
	@PreAuthorize("hasAuthority('ADMIN')") // metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value = "/lisaaTuote")
	public String lisaaTuote(Model model) {
		model.addAttribute("tuote", new Tuote());
		model.addAttribute("tuoteluokat", tuoteluokkarepository.findAll());

		return "lisaaTuote";
	}

	// Tallennetaan tuote, oikeus vain adminilla
	@PreAuthorize("hasAuthority('ADMIN')") // metoditason tarkistus onko oikeus
	@RequestMapping(value = "/tallennaTuote", method = RequestMethod.POST) // tämä käytössä lisaaTuote.html :ssä
	public String tallennaTuote(@Valid Tuote tuote, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Validointivirhe/tyhjä kenttä");
			model.addAttribute("tuote", tuote);
			model.addAttribute("tuoteluokat", tuoteluokkarepository.findAll()); // <---- tämän takia edellinen kaatui,
																				// tärkeä
			return "lisaaTuote";
		}
		tuoterepository.save(tuote);
		System.out.println("Tallennetaan " + tuote);
		return "redirect:/tuotelista";
	}

	// Muokataan tuotetta, oikeus vain adminilla
	@PreAuthorize("hasAuthority('ADMIN')") // metoditason tarkistus, onko oikeus muokata
	@RequestMapping(value = "/muokkaaTuote/{id}", method = RequestMethod.GET)
	public String muokkaaTuote(@PathVariable("id") Long tuoteid, Model model) {
		model.addAttribute("tuote", tuoterepository.findById(tuoteid)); // tuotteen ID
		model.addAttribute("tuoteluokat", tuoteluokkarepository.findAll());

		return "muokkaaTuote";
	}

	// TUOTELUOKAT: hae kaikki, poista, lisää, muokkaa

	// Haetaan ja listataan tuoteluokat, oikeus sekä adminilla että userilla
	@RequestMapping(value = "tuoteluokat")
	public String tuoteluokat(Model model) {
		model.addAttribute("tuoteluokat", tuoteluokkarepository.findAll());// haetaan tietokannasta näytettäväksi
																			// ("nimi", arvo)
		System.out.println("Näytetään tuoteluokat");
		return "tuoteluokat"; // templaten nimi, .html, view --> src/main/resources/templates

	}

	/*
	 * vaihdettu pois, koska kun poistaa tuoteluokan, myös kyseisessä tuoteluokassa
	 * olevat tuotteet poistetaan
	 * 
	 * //Poistetaan tuoteluokka
	 * 
	 * @RequestMapping(value="/poista/{id}", method=RequestMethod.GET) public String
	 * deleteTuoteluokka(@PathVariable("id") Long tuoteluokkaid, Model model) {
	 * tuoteluokkarepository.deleteById(tuoteluokkaid);
	 * System.out.println("poistettu tuoteluokka, id: " + tuoteluokkaid); return
	 * "redirect:../tuoteluokat";
	 * 
	 * }
	 */

	// poistaminen   v1.3. päivitetty, tuoteluokkaa ei voi poistaa jos siinä on tuotteita
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus poistaa
	@GetMapping("poista/{id}")
	public String deleteTuoteluokka(@PathVariable("id") Long id, Model model) {
		System.out.println("poista tuoteluokka, id " + id);
		if (tuoteluokkarepository.findById(id).get().getTuotteet().isEmpty()) {
			tuoteluokkarepository.deleteById(id);
		} else {
			System.out.println("Tuoteluokkaa ei voi poistaa, koska siinä on tuotteita");
		}

		return "redirect:../tuoteluokat";
	}

	// Lisätään tuoteluokka, vain admin
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus lisätä
	@RequestMapping(value = "/lisaaTuoteluokka")
	public String lisaaTuoteluokka(Model model) {
		model.addAttribute("tuoteluokka", new Tuoteluokka());
		// model.addAttribute("tuoteluokat", tuoteluokkarepository.findAll());
		return "lisaaTuoteluokka"; // thymeleaf-template
	}

	// Tallennetaan tuoteluokka, vain admin
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus
	@RequestMapping(value = "/tallennaTuoteluokka", method = RequestMethod.POST) // tämä käytössä lisaaTuoteluokka.html
																					// :ssä
	public String tallennaTuoteluokka(@Valid Tuoteluokka tuoteluokka, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Validointivirhe/tyhjä kenttä");
			model.addAttribute("tuoteluokka", tuoteluokka);
			// model.addAttribute("tuoteluokat", tuoteluokkarepository.findAll()); // <----
			// tämän takia edellinen kaatui, tärkeä
			return "lisaaTuoteluokka";
		}
		tuoteluokkarepository.save(tuoteluokka);
		System.out.println("Lisättiin tuoteluokka" + tuoteluokka);
		return "redirect:/tuoteluokat";
	}

	// Muokataan tuoteluokkaa, vain admin
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus
	@RequestMapping(value = "/muokkaaTuoteluokka/{id}", method = RequestMethod.GET)
	public String muokkaaTuoteluokka(@PathVariable("id") Long tuoteluokkaid, Model model) {
		model.addAttribute("tuoteluokka", tuoteluokkarepository.findById(tuoteluokkaid)); // tuoteluokan ID
		// model.addAttribute("tuoteluokat", tuoteluokkarepository.findAll());
		return "muokkaaTuoteluokka";
	}

}
