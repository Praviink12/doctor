package com.mindtree.doctor.test;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.doctor.controller.DoctorController;
import com.mindtree.doctor.entity.Doctor;
import com.mindtree.doctor.repository.DoctorRepository;
import com.mindtree.doctor.service.DoctorService;
	
	@WebMvcTest(DoctorController.class)
	public class DoctorTest {
	  @MockBean
	  private DoctorRepository doctorRepository;
	  
	  @MockBean
	  private DoctorService doctorService;

	  @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;

	  @Test
	  void shouldCreateDoctor() throws Exception {
	    Doctor doctor=new Doctor(4, "Sham", "M", "O&G", 8, 20);

	    mockMvc.perform(post("/doctors").contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(doctor)))
	        .andExpect(status().isOk())
	        .andDo(print());
	  }

	  @Test
	  void shouldReturnDoctor() throws Exception {
	    int id=9;
	    String name="Mona";
	    Doctor doctor=new Doctor(4, "Sham", "M", "O&G", 8, 20);

	    when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
	    mockMvc.perform(get("/doctors/get/{id}", id)).andExpect(status().isOk())
	        .andDo(print());
	  }
	  


	  @Test
	  void shouldReturnListOfDoctors() throws Exception {
	    List<Doctor> doctors = new ArrayList<>(
	        Arrays.asList(new Doctor(4, "Sham", "M", "O&G", 8, 20),
	            new Doctor(3, "Ram", "M", "O&G", 7, 24),
	            new Doctor(1, "Bheem", "M", "O&G", 9, 25)));

	    when(doctorRepository.findAll()).thenReturn(doctors);
	    mockMvc.perform(get("/doctors/"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.size()").value(doctors.size()))
	        .andDo(print());
	  }

}