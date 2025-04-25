package com.courseenrollment.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.courseenrollment.dto.ResponseStructure;
import com.courseenrollment.entity.Course;
import com.courseenrollment.entity.Instructor;
import com.courseenrollment.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseservice;
	
		@PostMapping
		public ResponseEntity<ResponseStructure<Course>> saveCourse(@RequestBody Course course){
			return courseservice.saveCourse(course);
		
	}
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Course>>> getCourse(){
			return courseservice.getCourse();   
	}
		
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Course>> getCourseById(@PathVariable int id){
			return courseservice.getCourseById(id);   
	}
		
		@PutMapping
		public ResponseEntity<ResponseStructure<Course>> updateCourse(@RequestBody Course course) {
		    return courseservice.updateCourse(course);
	}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteCourse(@PathVariable int id) {
		    return courseservice.deleteCourse(id);
	}
		
		@GetMapping("/instructor/{id}")
		public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByInstructor(@PathVariable int id) {
		    return courseservice.getCoursesByInstructorId(id);
		}
}
