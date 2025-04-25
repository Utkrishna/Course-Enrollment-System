package com.courseenrollment.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.courseenrollment.dto.ResponseStructure;
import com.courseenrollment.entity.Instructor;
import com.courseenrollment.entity.Student;
import com.courseenrollment.service.InstructorService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorservice;
	
		@PostMapping
		public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(@RequestBody Instructor instructor){
			return instructorservice.saveInstructor(instructor);
		
	}
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Instructor>>> getInstructor(){
			return instructorservice.getInstructor();   
	}
		
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(@PathVariable int id){
			return instructorservice.getInstructorById(id);   
	}
		
		@PutMapping
		public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(@RequestBody Instructor instructor) {
		    return instructorservice.updateInstructor(instructor);
	}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteInstructor(@PathVariable int id) {
		    return instructorservice.deleteInstructor(id);
	}
		
		@GetMapping("/student/{id}")
		public ResponseEntity<ResponseStructure<List<Student>>> getStudentByInstructorId(@PathVariable int id){
			return instructorservice.getStudentByInstructorId(id);
		}
}
