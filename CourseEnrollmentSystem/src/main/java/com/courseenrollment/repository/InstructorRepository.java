package com.courseenrollment.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.courseenrollment.entity.Instructor;
import com.courseenrollment.entity.Student;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
	
	@Query("SELECT DISTINCT e.student FROM Enrolment e WHERE e.course.instructor.id = :instructorId")
	List<Student> findDistinctStudentsByInstructorId(int instructorId);

}
