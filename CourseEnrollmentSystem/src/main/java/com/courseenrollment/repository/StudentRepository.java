package com.courseenrollment.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.courseenrollment.entity.Course;
import com.courseenrollment.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Query("SELECT e.course FROM Student s JOIN s.enrolments e WHERE s.id = :studentId")
	public List<Course> findCoursesByStudentId(int studentId);
	
	
}
