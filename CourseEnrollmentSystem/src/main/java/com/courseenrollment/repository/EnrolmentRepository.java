package com.courseenrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.courseenrollment.entity.Enrolment;

public interface EnrolmentRepository extends JpaRepository<Enrolment, Integer>{
	
	@Query("select e from Enrolment e where e.student.id=:studentid")
	List<Enrolment> findByInstructorId(int studentid);
	
	@Query("select e from Enrolment e where e.course.id=:courseid")
	List<Enrolment> findByCourseId(int courseid);
	
}
