package com.courseenrollment.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.courseenrollment.dto.ResponseStructure;
import com.courseenrollment.entity.Course;
import com.courseenrollment.entity.Student;
import com.courseenrollment.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
		
	@Autowired
	private StudentService studentservice;
	
		@PostMapping
		public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student){
			return studentservice.saveStudent(student);
		
	}
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Student>>> getStudent(){
			return studentservice.getStudent();   
	}
		
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Student>> getStudentById(@PathVariable int id){
			return studentservice.getStudentById(id);   
	}
		
		@PutMapping
		public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestBody Student student) {
		    return studentservice.updateStudent(student);
	}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteStudent(@PathVariable int id) {
		    return studentservice.deleteStudent(id);
	}
		
		@GetMapping("/courses/{id}")
		public ResponseEntity<ResponseStructure<List<Course>>> getCourseByStudentId(@PathVariable int id){
			return studentservice.getCourseByStudentId(id);
		}
			
}