package com.courseenrollment.dao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.courseenrollment.entity.Instructor;
import com.courseenrollment.entity.Student;
import com.courseenrollment.repository.InstructorRepository;

@Repository
public class InstructorDAO {

	@Autowired
	private InstructorRepository instructorrep;
	
	public Instructor saveInstructor(Instructor instructor) {
		instructorrep.save(instructor);
		return instructor;
	}
	
	public List<Instructor> getInstructor() {
		List<Instructor> instructor=instructorrep.findAll();
		return instructor;
	}
	
	public Optional<Instructor> getInstructorById(int id) {
		return instructorrep.findById(id);
	}
	
	public Instructor updateInstructor(Instructor instructor) {
		return instructorrep.save(instructor);
	}
	
	public boolean deleteInstructor(int id) {
	    if (instructorrep.existsById(id)) {
	    	instructorrep.deleteById(id);
	        return true;
	    } else {
	        return false;
	    }
	}

	public List<Student> getStudentByInstructorId(int instructorid) {
		return instructorrep.findDistinctStudentsByInstructorId(instructorid);
	}

}
