package com.school.SpringSecuritywithDatabase.dao;

import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesTakenDao extends JpaRepository<CoursesTaken, Integer> {
//    @Query("Select c from CoursesTaken c where c.student.id = :studentId")
//    List<CoursesTaken> findByStudent(@Param("studentId") int studentId);
}
