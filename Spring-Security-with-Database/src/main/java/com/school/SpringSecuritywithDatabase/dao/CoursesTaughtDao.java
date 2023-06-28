package com.school.SpringSecuritywithDatabase.dao;

import com.school.SpringSecuritywithDatabase.model.Courses;
import com.school.SpringSecuritywithDatabase.model.CoursesTaught;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesTaughtDao extends JpaRepository<CoursesTaught, Integer> {
    @Query("Select c.course from CoursesTaught c where c.professor.id= :professorId")
    List<Courses> findAllCoursesByProfessorId(int professorId);
}
