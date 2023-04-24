//Controller, sovelluksen endpointit
package fi.haagahelia.harkka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.harkka.domain.TuoteRepository;
//import fi.haagahelia.harkka.domain.TuoteluokkaRepository;

@Controller
public class TuoteController {
	
	//injektoidaan TuoteRepository ja TuoteluokkaRepository Controller-luokkaan
	@Autowired
	private TuoteRepository tuoterepository;
	
	//@Autowired muista import
	//private TuoteluokkaRepository tuoteluokkarepository;
	
	
	@RequestMapping("/tuotelista") 
	public String tuotelista(Model model) { 
		model.addAttribute("tuotteet", tuoterepository.findAll() ) ;//haetaan tietokannasta näytettäväksi ("nimi", arvo)
		
		return "tuotelista"; //templaten nimi, .html, view --> src/main/resources/templates
		
	}

}
