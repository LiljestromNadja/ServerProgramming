package k23.HelloAppWeek2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloAppController {
	
	//Tehtävä 1: Hello, if, greater than, lower than
	@GetMapping("hello") //urlin endpoint
	public String sayGreetings(@RequestParam (name="name") String name, 
			@RequestParam(name="age") int ika,
			Model model) {
		model.addAttribute("nimi", name);
		model.addAttribute("ika", ika);
		
		return "hello2"; //html -tiedosto johon viitataan
	}

}
