package com.school.SpringSecuritywithDatabase.dao;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CoursesTakenDao extends JpaRepository<CoursesTaken, Integer> {

    @Query("Select c.course from CoursesTaken c where c.student.id= :studentId")
    List<Course> findAllCoursesByStudentId(int studentId);

}
