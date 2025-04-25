package com.courseenrollment.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import com.courseenrollment.dao.StudentDAO;
import com.courseenrollment.dto.ResponseStructure;
import com.courseenrollment.entity.Course;
import com.courseenrollment.entity.Student;
import com.courseenrollment.exception.EmptyObjects;
import com.courseenrollment.exception.IdNotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentDAO studentdao;
	
	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student) {
		studentdao.saveStudent(student);
		ResponseStructure<Student> response=new ResponseStructure<Student>();
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("Student data Saved Succesfully");
		response.setData(student);
		return new ResponseEntity<ResponseStructure<Student>>(response,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> getStudent() {
		List<Student> student= studentdao.getStudent();
		ResponseStructure<List<Student>> response=new ResponseStructure<List<Student>>();
	        	if(student.size()>0) {
	        		response.setStatuscode(HttpStatus.OK.value());
		            response.setMessage("Students fetched successfully");
		            response.setData(student);
		            return new ResponseEntity<ResponseStructure<List<Student>>>(response,HttpStatus.OK);
	        	}
	            
	            else {
	            	throw new EmptyObjects();
	            }
	       
	}
	
	public ResponseEntity<ResponseStructure<Student>> getStudentById(int id) {
		Optional<Student> opt=studentdao.getStudentById(id);
		ResponseStructure<Student> response=new ResponseStructure<Student>();
		if (opt.isPresent()) {
		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Student found using Id");
		    response.setData(opt.get());
		    return  new ResponseEntity<ResponseStructure<Student>>(response,HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateStudent(Student student) {
		Optional<Student> existing = studentdao.getStudentById(student.getId()); 
	    ResponseStructure<Student> response = new  ResponseStructure<Student>();

	    if (existing.isPresent()) {
	        Student updated = studentdao.updateStudent(student); 
	        response.setStatuscode(HttpStatus.OK.value());
	        response.setMessage("Student updated successfully");
	        response.setData(updated);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	    	throw new IdNotFoundException();
	    }
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteStudent(int id) {
	    ResponseStructure<String> response = new ResponseStructure<String>();
	    if (studentdao.deleteStudent(id)) {
	        response.setStatuscode(HttpStatus.OK.value());
	        response.setMessage("Student deleted successfully");
	        response.setData("Deleted ID: " + id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	    	throw new IdNotFoundException(); 
	    }
	}

	public ResponseEntity<ResponseStructure<List<Course>>> getCourseByStudentId(int id) {
		List<Course> courses=studentdao.getCourseByStudentId(id);
		ResponseStructure<List<Course>> response=new ResponseStructure<List<Course>>();
		if (courses.size()>0) {
		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Courses is found By Using StudentId!!!");
		    response.setData(courses);
		    return  new ResponseEntity<ResponseStructure<List<Course>>>(response,HttpStatus.OK);
		} else {
			throw new EmptyObjects();
		}
		
	}

}
