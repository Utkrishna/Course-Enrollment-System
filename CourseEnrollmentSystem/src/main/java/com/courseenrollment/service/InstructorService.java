 package com.courseenrollment.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.courseenrollment.dao.InstructorDAO;
import com.courseenrollment.dto.ResponseStructure;
import com.courseenrollment.entity.Enrolment;
import com.courseenrollment.entity.Instructor;
import com.courseenrollment.entity.Student;
import com.courseenrollment.exception.EmptyObjects;
import com.courseenrollment.exception.IdNotFoundException;

@Service
public class InstructorService {
	
	@Autowired
	private InstructorDAO instructordao;
	
	public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(Instructor student) {
		instructordao.saveInstructor(student);
		ResponseStructure<Instructor> response=new ResponseStructure<Instructor>();
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("Instructor data Saved Succesfully");
		response.setData(student);
		return new ResponseEntity<ResponseStructure<Instructor>>(response,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Instructor>>> getInstructor() {
		List<Instructor> student= instructordao.getInstructor();
		if(student.size()>0) {
			ResponseStructure<List<Instructor>> response=new ResponseStructure<List<Instructor>>();
		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Instructors fetched successfully");
		    response.setData(student);
		    return new ResponseEntity<ResponseStructure<List<Instructor>>>(response,HttpStatus.OK);
		}
	    
	    else {
	    	throw new EmptyObjects();
	    }
	        
	}
	
	public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(int id) {
		Optional<Instructor> opt=instructordao.getInstructorById(id);
		ResponseStructure<Instructor> response=new ResponseStructure<Instructor>();
		if (opt.isPresent()) {
		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Instructor found Using Id");
		    response.setData(opt.get());
		    return  new ResponseEntity<ResponseStructure<Instructor>>(response,HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(Instructor student) {
		Optional<Instructor> existing = instructordao.getInstructorById(student.getId()); 
	    ResponseStructure<Instructor> response = new  ResponseStructure<Instructor>();

	    if (existing.isPresent()) {
	    	Instructor updated = instructordao.updateInstructor(student); 
	        response.setStatuscode(HttpStatus.OK.value());
	        response.setMessage("Instructor updated successfully");
	        response.setData(updated);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	    	throw new IdNotFoundException();
	    }
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteInstructor(int id) {
	    ResponseStructure<String> response = new ResponseStructure<String>();
	    if (instructordao.deleteInstructor(id)) {
	        response.setStatuscode(HttpStatus.OK.value());
	        response.setMessage("Instructor deleted successfully");
	        response.setData("Deleted ID: " + id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	    	throw new IdNotFoundException();
	    }
	}

	public ResponseEntity<ResponseStructure<List<Student>>> getStudentByInstructorId(int id) {
		List<Student> students=instructordao.getStudentByInstructorId(id);
		ResponseStructure<List<Student>> response=new ResponseStructure<List<Student>>();
		if(students.size()>0) {
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("Students Are founded By using InstructorId!!!");
			response.setData(students);
			return new ResponseEntity<ResponseStructure<List<Student>>>(response,HttpStatus.OK);
		}
		else {
			throw new EmptyObjects();
		}
	}
}
