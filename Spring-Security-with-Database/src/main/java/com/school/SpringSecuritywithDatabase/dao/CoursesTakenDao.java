package com.school.SpringSecuritywithDatabase.dao;
import com.school.SpringSecuritywithDatabase.dto.CourseDTO;
import com.school.SpringSecuritywithDatabase.dto.StudentDTO;
import com.school.SpringSecuritywithDatabase.enums.Grade;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CoursesTakenDao extends JpaRepository<CoursesTaken, Integer> {

    @Query("Select c.course from CoursesTaken c where c.student.id= :studentId")
    List<Course> findAllCoursesByStudentId(int studentId);



    @Query("Select ct.course.name from CoursesTaken ct where ct.student.name = :name")
    List<String> findAllCoursesByStudentName(String name);

    @Query("Select new com.school.SpringSecuritywithDatabase.dto.CourseDTO" +
            "(ct.course.name, ct.grade, ct.student.name) from CoursesTaken ct where ct.student.name = :name")
    List<CourseDTO> findAllCoursesByStudentNameDto(String name);

    @Query("Select count(distinct ct.student.id) from CoursesTaken ct where ct.course.name = :name")
    Integer findNumberOfCourseTakenByStudents(String name);

    @Query("Select new com.school.SpringSecuritywithDatabase.dto.StudentDTO(ct.student.name) from CoursesTaken ct where ct.grade =:grade and ct.course.name =:course")
    List<StudentDTO> findAllStudents(Grade grade, String course);

    @Query("SELECT ct.course.name FROM CoursesTaken ct " +
            "WHERE ct.student.id = :studentId " +
            "AND ct.course IN (SELECT ct1.course FROM CoursesTaught ct1 WHERE ct1.professor.id = :professorId)")
    List<String> findByStudentAndProfessor(
            @Param("studentId") int studentId,
            @Param("professorId") int professorId);
}
