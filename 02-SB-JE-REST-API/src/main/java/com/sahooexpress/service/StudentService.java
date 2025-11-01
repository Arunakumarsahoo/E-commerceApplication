package com.sahooexpress.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.sahooexpress.entities.Student;
import com.sahooexpress.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	public Student create(Student student) {
		return studentRepository.save(student);
		
	}
	
	public Student fetch(Integer id) {
	Optional<Student> optional	= studentRepository.findById(id);
	if (optional.isPresent()) {
		Student dbStudent= optional.get();
		System.out.println(dbStudent.getEmail());
		return dbStudent;
	}else {
		throw new RuntimeException("Student Id is not found in db..");
		}
	}
	
	public void update(Student student) {
		Student dbStudent = fetch(student.getId());
		dbStudent.setEmail(student.getEmail());
		dbStudent.setName(student.getName());
		studentRepository.save(dbStudent);

		
	}
	
	public void delete(Integer id) {
		studentRepository.deleteById(id);
	}
	
	

	
}
