package com.school.SpringSecuritywithDatabase.dao;

import com.school.SpringSecuritywithDatabase.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoursesDao  extends JpaRepository<Courses, Integer> {

}
