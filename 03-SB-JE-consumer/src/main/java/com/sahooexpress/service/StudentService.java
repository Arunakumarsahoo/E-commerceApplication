package com.sahooexpress.service;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.sahooexpress.model.Student;

import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Student createStudent(Student student) {
		//We need to call external MS
		String url = "http://localhost:8080/students"; //Producer
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		
		HttpEntity<Student> entity = new HttpEntity<>(student,headers);
		
		try {
			var response = restTemplate.exchange(url, HttpMethod.POST, entity, Student.class);

	        return response.getBody();
	    } catch (HttpClientErrorException | HttpServerErrorException ex) {
	        log.error("HTTP error: {}", ex.getResponseBodyAsString(), ex);
	        throw new RuntimeException("HTTP error occurred while creating student", ex);
	    } catch (ResourceAccessException ex) {
	        log.error("Resource access error: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Network error occurred while calling producer service", ex);
	    } catch (Exception ex) {
	        log.error("Unexpected error: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Unexpected error occurred", ex);
	    }
			
	}
		
}
