package com.school.SpringSecuritywithDatabase.dao;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {
    String deleteById(int id);


}
