package k23.HelloAppWeek2.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FriendController {
	
	//tehtävä 3
	private ArrayList<String> friends = new ArrayList<>();
	
	@GetMapping("/friend")
	public String handleFriends(@RequestParam(name="nimi", required = false) String name, Model model) {
		if (name != null) {
			friends.add(name);
		}
		model.addAttribute("friends", friends);
		return "friends";
	}
	
	
	
	

}
