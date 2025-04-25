package com.courseenrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.courseenrollment.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	@Query("select c from Course c where c.instructor.id=:instructorId")
	List<Course> findByInstructorId(int instructorId);

}
