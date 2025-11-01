package com.sahooexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahooexpress.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	

}
