package com.courseenrollment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.courseenrollment.dao.CourseDAO;
import com.courseenrollment.dao.StudentDAO;
import com.courseenrollment.dto.ResponseStructure;
import com.courseenrollment.entity.Course;
import com.courseenrollment.entity.Instructor;
import com.courseenrollment.entity.Student;
import com.courseenrollment.exception.EmptyObjects;
import com.courseenrollment.exception.IdNotFoundException;
import com.courseenrollment.repository.InstructorRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseDAO coursedao;
	
	@Autowired
	private InstructorRepository instrep;
	
	public ResponseEntity<ResponseStructure<Course>> saveCourse(Course course) {
		int instructorId = course.getInstructor() != null ? course.getInstructor().getId() : 0;
        Optional<Instructor> optionalInstructor = instrep.findById(instructorId);
        if (optionalInstructor.isEmpty()) {
            ResponseStructure<Course> errorResponse = new ResponseStructure<>();
            errorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
            errorResponse.setMessage("Invalid Instructor ID!!!");
            errorResponse.setData(null);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        course.setInstructor(optionalInstructor.get());
        Course savedCourse = coursedao.saveCourse(course);

        ResponseStructure<Course> response = new ResponseStructure<>();
        response.setStatuscode(HttpStatus.CREATED.value());
        response.setMessage("Course data Saved Successfully!!!");
        response.setData(savedCourse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
	
	public ResponseEntity<ResponseStructure<List<Course>>> getCourse() {
		List<Course> course= coursedao.getCourse();
		ResponseStructure<List<Course>> response=new ResponseStructure<List<Course>>();
	        	if(course.size()>0) {
	        		response.setStatuscode(HttpStatus.OK.value());
		            response.setMessage("Courses fetched successfully!!!");
		            response.setData(course);
		            return new ResponseEntity<ResponseStructure<List<Course>>>(response,HttpStatus.OK);
	        	}
	        
	            else {
	            	throw new EmptyObjects();
	            }
	}
	
	public ResponseEntity<ResponseStructure<Course>> getCourseById(int id) {
		Optional<Course> opt=coursedao.getCourseById(id);
		ResponseStructure<Course> response=new ResponseStructure<Course>();
		if (opt.isPresent()) {
		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Courses fetched successfully By Using Id !!!!");
		    response.setData(opt.get());
		    return  new ResponseEntity<ResponseStructure<Course>>(response,HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Course>> updateCourse(Course course) {
		Optional<Course> existing = coursedao.getCourseById(course.getId());
        ResponseStructure<Course> response = new ResponseStructure<>();

        if (existing.isPresent()) {
            if (course.getInstructor() != null && course.getInstructor().getId() != 0) {
                Optional<Instructor> instructorOpt = instrep.findById(course.getInstructor().getId());
                instructorOpt.ifPresent(course::setInstructor);
            }

            Course updated = coursedao.updateCourse(course);
            response.setStatuscode(HttpStatus.OK.value());
            response.setMessage("Course updated successfully!!!");
            response.setData(updated);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException();
        }
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteCourse(int id) {
	    ResponseStructure<String> response = new ResponseStructure<String>();
	    if (coursedao.deleteCourse(id)) {
	        response.setStatuscode(HttpStatus.OK.value());
	        response.setMessage("Course deleted successfully!!!");
	        response.setData("Deleted ID: " + id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	    	throw new IdNotFoundException();
	    }
	}

	public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByInstructorId(int id) {
		List<Course> courses=coursedao.getCoursesByInstructorId(id);
		ResponseStructure<List<Course>> response=new ResponseStructure<List<Course>>();
		if(courses.size()>0) {
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("Courses Are founded!!!");
			response.setData(courses);
			return new ResponseEntity<ResponseStructure<List<Course>>>(response,HttpStatus.OK);
		}
		else {
			throw new EmptyObjects();
		}
	}
}
