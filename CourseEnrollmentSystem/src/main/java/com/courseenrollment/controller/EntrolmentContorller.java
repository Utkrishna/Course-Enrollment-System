package com.courseenrollment.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.courseenrollment.dto.ResponseStructure;
import com.courseenrollment.entity.Course;
import com.courseenrollment.entity.Enrolment;
import com.courseenrollment.service.EnrolmentService;

@RestController
@RequestMapping("/enrolment")
public class EntrolmentContorller {
	
	@Autowired
	private EnrolmentService enrolmentservice;
	
		@PostMapping
		public ResponseEntity<ResponseStructure<Enrolment>> saveEnrolment(@RequestBody Enrolment enrolment){
			return enrolmentservice.saveEnrolment(enrolment);
		
	}
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Enrolment>>> getEnrolment(){
			return enrolmentservice.getEnrolment();   
	}
		
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Enrolment>> getEnrolmentById(@PathVariable int id){
			return enrolmentservice.getEnrolmentById(id);   
	}
		
		@PutMapping
		public ResponseEntity<ResponseStructure<Enrolment>> updateEnrolment(@RequestBody Enrolment enrolment) {
		    return enrolmentservice.updateEnrolment(enrolment);
	}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteEnrolment(@PathVariable int id) {
		    return enrolmentservice.deleteEnrolment(id);
	}
		
		@GetMapping("/student/{id}")
		public ResponseEntity<ResponseStructure<List<Enrolment>>> getEnrolByStudentId(@PathVariable int id) {
		    return enrolmentservice.getEnrolByStudentId(id);
		}
		
		@GetMapping("/course/{id}")
		public ResponseEntity<ResponseStructure<List<Enrolment>>> getEnrolByCourseId(@PathVariable int id) {
		    return enrolmentservice.getEnrolByCourseId(id);
		}
}
