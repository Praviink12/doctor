package com.mindtree.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.doctor.entity.Doctor;
import com.mindtree.doctor.service.DoctorService;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
public class DoctorController {
	
	@Autowired
	private DoctorService dserve;
	
	@PostMapping("/doctors")
	public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {
		return dserve.saveDoctor(doctor);
	}
	
	
	@GetMapping("/doctors")
	public List<String> getDoctors(){
		return dserve.getDoctors();
	}
	
	
	@GetMapping("/doctors/")
	public List<Doctor> getAllDoctors(){
		return dserve.getAllDoctors();
	}
	
	
	@GetMapping("/{docName}")
	public Doctor getDoctorByName(@PathVariable String docName) {
		return dserve.getDoctorByName(docName);
	}
	
	
	@GetMapping("doctors/get/{id}")
	public Doctor getDoctorById(@PathVariable int id) {
		return dserve.getDoctorById(id);
	}
	
	
}
