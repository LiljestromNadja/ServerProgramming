package fi.haagahelia.harkka.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.harkka.domain.ApplicationUser;
import fi.haagahelia.harkka.domain.ApplicationUserRepository;
import fi.haagahelia.harkka.service.ApplicationUserRegistrationForm;
import jakarta.validation.Valid;

@Controller
public class ApplicationUserRegistrationController {

	private static final Logger log = LoggerFactory.getLogger(ApplicationUserRegistrationController.class);

	@Autowired
	ApplicationUserRepository regUserRepository;

	@GetMapping("/register")
	public String addNewApplicationUser(Model model) {
		log.info("new user template " + new ApplicationUserRegistrationForm());
		model.addAttribute("newuser", new ApplicationUserRegistrationForm());
		return "registration";

	}

	@PostMapping("/saveuser")
	public String saveUser(@Valid @ModelAttribute("newuser") ApplicationUserRegistrationForm newUser,
			BindingResult bindingResult) {

		log.info("saveuser: uusi käyttäjä " + newUser.toString());
		if (!bindingResult.hasErrors()) { // validation errors
			if (newUser.getPassword().equals(newUser.getPasswordCheck())) { // check password match
				String pwd = newUser.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				ApplicationUser newAppUser = new ApplicationUser();
				newAppUser.setPasswordHash(hashPwd);
				newAppUser.setUsername(newUser.getUsername());
				newAppUser.setRole("USER");
				if (regUserRepository.findByUsername(newUser.getUsername()) == null) { // Check if user exists
					regUserRepository.save(newAppUser);
				} else {
					log.info("Käyttäjänimi on varattu");
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "registration";
				}
			} else {
				log.info("Salasanat eivät täsmää");
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "registration";
			}
		} else {
			return "registration";
		}
		return "redirect:/userlist";
	}

	// Listaa kaikki käyttäjät
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/userlist" }) // endpoint: http://localhost:8080/userlist
	public String userlist(Model model) {

		model.addAttribute("users", regUserRepository.findAll());

		return "userlist";
	}
	
	// Poistetaan käyttäjä, oikeus vain adminilla
		@PreAuthorize("hasAuthority('ADMIN')") // metoditason tarkistus onko oikeus poistaa
		@RequestMapping(value = "/poistakayttaja/{id}", method = RequestMethod.GET)
		public String delete(@PathVariable("id") Long id, Model model) {
			regUserRepository.deleteById(id);
			System.out.println("poistettu käyttäjä, id: " + id);
			return "redirect:../userlist";
		}

}
