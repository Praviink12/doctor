package com.mindtree.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindtree.doctor.entity.Doctor;
import com.mindtree.doctor.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository drepo;
	
	public ResponseEntity<Doctor> saveDoctor(Doctor doctor) {
		return new ResponseEntity<Doctor>(drepo.save(doctor),HttpStatus.CREATED);
	}
	

	public List<String> getDoctors() {
		return drepo.findAllByName();
	}
	

	public Doctor getDoctorByName(String docName) {
		return drepo.getDoctorByName(docName);
	}
	

	public List<Doctor> getAllDoctors() {
		return drepo.findAll();
	}
	

	public Doctor getDoctorById(int id) {
	   	return drepo.findById(id).get();
	}

}
