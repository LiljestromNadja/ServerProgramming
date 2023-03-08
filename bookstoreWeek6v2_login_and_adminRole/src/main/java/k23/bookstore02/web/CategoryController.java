package k23.bookstore02.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import k23.bookstore02.domain.Category;
import k23.bookstore02.domain.CategoryRepository;

@Controller //palauttaa HTML-sivun
public class CategoryController {


	@Autowired
	CategoryRepository crepository;

	@GetMapping("/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", crepository.findAll());
		return "categories";
	}

	@GetMapping("/addCategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addCategory";
	}

	@PostMapping("/saveCategory")
	public String saveCategory(@Valid Category category, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened");
			return "addCategory";
		}
		crepository.save(category);
		return "redirect:categories";

	}

}

