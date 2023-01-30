package k23.HelloAppWeek2.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import k23.HelloAppWeek2.domain.Student;

@Controller
public class StudentController {
	
	//tehtävä 2: Listojen käsittely
	@GetMapping("hellos") //urlin endpoint
	public String helloStudents(Model model) {
		ArrayList<Student> students = new ArrayList<>();
		
		students.add(new Student("Kate", "Cole"));
		students.add(new Student("Dan", "Brown"));
		students.add(new Student("Mike", "Mars"));
		
		model.addAttribute("students", students);
		return "studentList";
	}

}
