package com.school.SpringSecuritywithDatabase.dao;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {
    String deleteById(int id);

    @Query("Select s from Student s where s.user is null")
    List<Student> findAllStudentsTWithoutUserId();


}
