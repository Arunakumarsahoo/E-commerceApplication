package com.sahooexpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sahooexpress.entities.Student;
import com.sahooexpress.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Student create(@RequestBody Student student) {
		return studentService.create(student);
	}
	
	@GetMapping("/{id}")
	public Student fetch(@PathVariable Integer id) {
		return studentService.fetch(id);
	}
	
	@PutMapping("/{studenId}")
	public void update(@RequestBody Student student, @PathVariable Integer studenId) {
		student.setId(studenId);
		studentService.update(student);
	}
	
	@DeleteMapping("/{studenId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer studenId) {
		studentService.delete(studenId);
	}
	
	//Password Reset - @PatchMapping (Whenever we have 1 or 2 varibale to update)
	
}
