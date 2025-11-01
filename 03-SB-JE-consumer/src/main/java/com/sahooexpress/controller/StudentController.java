package com.sahooexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sahooexpress.model.Student;
import com.sahooexpress.service.StudentService;

@RestController
@RequestMapping("/student/consumer") //Consumer
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody Student student) {
		 studentService.createStudent(student);
	}

}
