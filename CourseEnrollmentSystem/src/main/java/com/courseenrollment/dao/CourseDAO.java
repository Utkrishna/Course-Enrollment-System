package com.courseenrollment.dao;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.courseenrollment.entity.Course;
import com.courseenrollment.repository.CourseRepository;

@Repository
public class CourseDAO {
	
	@Autowired
	private CourseRepository courserep;;
	
	public Course saveCourse(Course course) {
		courserep.save(course);
		return course;
	}
	
	public List<Course> getCourse() {
		List<Course> course=courserep.findAll();
		return course;
	}
	
	public Optional<Course> getCourseById(int id) {
		return courserep.findById(id);
	}
	
	public Course updateCourse(Course course) {
		return courserep.save(course);
	}
	
	public boolean deleteCourse(int id) {
	    if (courserep.existsById(id)) {
	    	courserep.deleteById(id);
	        return true;
	    } else {
	        return false;
	    }
	}

	public List<Course> getCoursesByInstructorId(int instructorId ) {
		return courserep.findByInstructorId(instructorId);
	}
}
