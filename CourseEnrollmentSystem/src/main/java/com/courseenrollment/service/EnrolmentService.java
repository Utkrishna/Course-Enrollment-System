    package com.courseenrollment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.courseenrollment.dao.EnrolmentDAO;
import com.courseenrollment.dto.ResponseStructure;
import com.courseenrollment.entity.Course;
import com.courseenrollment.entity.Enrolment;
import com.courseenrollment.entity.Instructor;
import com.courseenrollment.entity.Student;
import com.courseenrollment.exception.EmptyObjects;
import com.courseenrollment.exception.IdNotFoundException;
import com.courseenrollment.repository.CourseRepository;
import com.courseenrollment.repository.StudentRepository;

@Service
public class EnrolmentService {

    @Autowired
    private EnrolmentDAO enrolmentdao;
    
    @Autowired
    private StudentRepository studentrep;
    
    @Autowired
    private CourseRepository courserep;

    public ResponseEntity<ResponseStructure<Enrolment>> saveEnrolment(Enrolment enrolment) {
    	int studentid = enrolment.getStudent() != null ? enrolment.getStudent().getId() : 0;
    	int courseid = enrolment.getCourse() != null ? enrolment.getCourse().getId() : 0;
        Optional<Student> optionalStudent = studentrep.findById(studentid);
        Optional<Course> optionalCourse = courserep.findById(courseid);
        if (optionalStudent.isEmpty() && optionalCourse.isEmpty()) {
            ResponseStructure<Enrolment> errorResponse = new ResponseStructure<>();
            errorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
            errorResponse.setMessage("Invalid Student ID or Invalid Course Id");
            errorResponse.setData(null);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        enrolment.setStudent(optionalStudent.get());
        enrolment.setCourse(optionalCourse.get());
        Enrolment enrolments = enrolmentdao.saveEnrolment(enrolment);

        ResponseStructure<Enrolment> response = new ResponseStructure<>();
        response.setStatuscode(HttpStatus.CREATED.value());
        response.setMessage("Enrolment data Saved Successfully");
        response.setData(enrolments);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Enrolment>>> getEnrolment() {
        List<Enrolment> enrolments = enrolmentdao.getEnrolment();
        ResponseStructure<List<Enrolment>> response = new ResponseStructure<>();

        if (enrolments.size()>0) {
            response.setStatuscode(HttpStatus.OK.value());
            response.setMessage("Enrolments fetched successfully");
            response.setData(enrolments);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	throw new EmptyObjects();
        }
    }

    public ResponseEntity<ResponseStructure<Enrolment>> getEnrolmentById(int id) {
        Optional<Enrolment> opt = enrolmentdao.getEnrolmentById(id);
        ResponseStructure<Enrolment> response = new ResponseStructure<>();

        if (opt.isPresent()) {
            response.setStatuscode(HttpStatus.OK.value());
            response.setMessage("Enrolment found By Id!!");
            response.setData(opt.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException();
        }
    }

    public ResponseEntity<ResponseStructure<Enrolment>> updateEnrolment(Enrolment enrolment) {
        Optional<Enrolment> existing = enrolmentdao.getEnrolmentById(enrolment.getId());
        ResponseStructure<Enrolment> response = new ResponseStructure<>();

        if (existing.isPresent()) {
            Enrolment updated = enrolmentdao.updateEnrolment(enrolment);
            response.setStatuscode(HttpStatus.OK.value());
            response.setMessage("Enrolment updated successfully");
            response.setData(updated);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException();
        }
    }

    public ResponseEntity<ResponseStructure<String>> deleteEnrolment(int id) {
        ResponseStructure<String> response = new ResponseStructure<>();

        if (enrolmentdao.deleteEnrolment(id)) {
            response.setStatuscode(HttpStatus.OK.value());
            response.setMessage("Enrolment deleted successfully");
            response.setData("Deleted Enrolment ID: " + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException();
        }
    }

	public ResponseEntity<ResponseStructure<List<Enrolment>>> getEnrolByStudentId(int id) {
		List<Enrolment> enrol=enrolmentdao.getEnrolByStudentId(id);
		ResponseStructure<List<Enrolment>> response=new ResponseStructure<List<Enrolment>>();
		if(enrol.size()>0) {
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("Enrolment is founded By Using StudentId!!!");
			response.setData(enrol);
			return new ResponseEntity<ResponseStructure<List<Enrolment>>>(response,HttpStatus.OK);
		}
		else {
			throw new EmptyObjects();
		}
	}

	public ResponseEntity<ResponseStructure<List<Enrolment>>> getEnrolByCourseId(int id) {
		List<Enrolment> enrol=enrolmentdao.getEnrolByCourseId(id);
		ResponseStructure<List<Enrolment>> response=new ResponseStructure<List<Enrolment>>();
		if(enrol.size()>0) {
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("Enrolment is founded By Using CourseId!!!");
			response.setData(enrol);
			return new ResponseEntity<ResponseStructure<List<Enrolment>>>(response,HttpStatus.OK);
		}
		else {
			throw new EmptyObjects();
		}
	}
} 
