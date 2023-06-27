package com.school.SpringSecuritywithDatabase.dao;

import com.school.SpringSecuritywithDatabase.model.CoursesTaught;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesTaughtDao extends JpaRepository<CoursesTaught, Integer> {
}
