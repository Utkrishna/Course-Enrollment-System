package com.courseenrollment.dao;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courseenrollment.entity.Course;
import com.courseenrollment.entity.Student;
import com.courseenrollment.repository.StudentRepository;

@Repository
public class StudentDAO {

	@Autowired
	private StudentRepository studentrep;
	
	public Student saveStudent(Student student) {
		studentrep.save(student);
		return student;
	}
	
	public List<Student> getStudent() {
		List<Student> student=studentrep.findAll();
		return student;
	}
	
	public Optional<Student> getStudentById(int id) {
		return studentrep.findById(id);
	}
	
	public Student updateStudent(Student student) {
		return studentrep.save(student);
	}
	
	public boolean deleteStudent(int id) {
	    if (studentrep.existsById(id)) {
	        studentrep.deleteById(id);
	        return true;
	    } else {
	        return false;
	    }
	}

	public List<Course> getCourseByStudentId(int id) {
		List<Course> course=studentrep.findCoursesByStudentId(id);
		return course;
	}

}