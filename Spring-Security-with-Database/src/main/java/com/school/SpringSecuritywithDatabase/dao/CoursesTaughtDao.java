package com.school.SpringSecuritywithDatabase.dao;

import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.CoursesTaught;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesTaughtDao extends JpaRepository<CoursesTaught, Integer> {
    @Query("Select c.course from CoursesTaught c where c.professor.id= :professorId")
    List<Course> findAllCoursesByProfessorId(int professorId);
    @Query("Select cT from CoursesTaught cT where cT.course.id=:courseId and cT.professor.id=:professorId")
    CoursesTaught findCourseTaughtByProfessorAndByCourse(int professorId, int courseId);
}
