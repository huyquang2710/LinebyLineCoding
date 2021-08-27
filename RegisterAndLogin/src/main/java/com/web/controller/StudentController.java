package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.entity.StudentEntity;
import com.web.service.IStudentService;

@Controller
public class StudentController {
	
	@Autowired
	private IStudentService studentService;
	
	@GetMapping("/students")
	public String getStudent(Model model) {
		model.addAttribute("students", studentService.getAllStudent());
		return "students";
	}
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		//create student
		StudentEntity student = new StudentEntity();
		model.addAttribute("student", student);
		return "createStudent";
	}
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") StudentEntity student) {
		studentService.save(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentById(@PathVariable Integer id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "editStudent";
	}
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Integer id, @ModelAttribute("student") StudentEntity student, Model model) {
		//get student by id from database
		StudentEntity existingStudent = studentService.getStudentById(id);
		existingStudent.setId(student.getId());
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		//save
		studentService.update(existingStudent);
		return "redirect:/students";
	} 
	@GetMapping("students/{id}")
	public String delete(@PathVariable Integer id) {
		studentService.delete(id);
		return "redirect:/students";
	}
}
