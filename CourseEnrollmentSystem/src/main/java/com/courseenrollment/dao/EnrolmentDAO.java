package com.courseenrollment.dao;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.courseenrollment.entity.Enrolment;
import com.courseenrollment.repository.EnrolmentRepository;

@Repository
public class EnrolmentDAO {
	
	@Autowired
	private EnrolmentRepository enrolmentrep;
	
	public Enrolment saveEnrolment(Enrolment enrolment) {
		enrolmentrep.save(enrolment);
		return enrolment;
	}
	
	public List<Enrolment> getEnrolment() {
		List<Enrolment> enrolment=enrolmentrep.findAll();
		return enrolment;
	}
	
	public Optional<Enrolment> getEnrolmentById(int id) {
		return enrolmentrep.findById(id);
	}
	
	public Enrolment updateEnrolment(Enrolment enrolment) {
		return enrolmentrep.save(enrolment);
	}
	
	public boolean deleteEnrolment(int id) {
	    if (enrolmentrep.existsById(id)) {
	    	enrolmentrep.deleteById(id);
	        return true;
	    } else {
	        return false;
	    }
	}

	public List<Enrolment> getEnrolByStudentId(int studentid) {
		return enrolmentrep.findByInstructorId(studentid);
	}

	public List<Enrolment> getEnrolByCourseId(int courseid) {
		return enrolmentrep.findByCourseId(courseid);
	}
}
