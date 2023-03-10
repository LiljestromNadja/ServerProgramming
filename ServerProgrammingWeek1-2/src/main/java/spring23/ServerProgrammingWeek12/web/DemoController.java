package spring23.ServerProgrammingWeek12.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
	
	//index
	@RequestMapping("index")
	@ResponseBody
	public String returnIndexPage() {
		return "This is the main page";
	}
	
	//contact
	@RequestMapping("contact")
	@ResponseBody
	public String returnContactPage() {
		return "This is the contact page";
	}
	
	//hello
	@RequestMapping("hello") //urliin http://localhost:8080/hello?annapaikka=Saari&annanimi=Kukalie  
	@ResponseBody
	public String paikkaJaAika(@RequestParam(name="annapaikka", required=false, defaultValue = "moon") String paikka, 
			@RequestParam(name="annanimi", required=false, defaultValue = "John")String nimi) {
		return "Welcome to the " + paikka + " " + nimi + "!"; //tyhjä http://localhost:8080/hello?annapaikka=&annanimi=
	}

	

}
