package com.school.SpringSecuritywithDatabase.service;
import com.school.SpringSecuritywithDatabase.dao.CourseDao;
import com.school.SpringSecuritywithDatabase.dao.CoursesTakenDao;
import com.school.SpringSecuritywithDatabase.dao.ProfessorDao;
import com.school.SpringSecuritywithDatabase.dao.StudentDao;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class StudentServiceImplMockitoTest extends AbstractServiceImplTest{

    private StudentDao studentDao;
    private CoursesTakenDao coursesTakenDao;
    private StudentServiceImpl studentServiceImpl;

    private ProfessorDao professorDao;

    private CourseDao courseDao;
    private Student student;

    private Course course;

    @BeforeEach
    void setUp() {
        studentDao = Mockito.mock(StudentDao.class);
        coursesTakenDao = Mockito.mock(CoursesTakenDao.class);
        studentServiceImpl = new StudentServiceImpl(studentDao,coursesTakenDao, professorDao, courseDao);
        this.course = new Course("Matematika", null, null);
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        this.student = new Student("Nina Jojic", courseList, null);

    }

    @Test
    void addStudent() {
        when(studentDao.save(any())).thenReturn(this.student);
        Student s1 = studentServiceImpl.create(this.student);
        assertNotNull(s1);
        assertEquals(this.student.getName(), s1.getName());
    }

    @Test
    void findAll() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(this.student);
        when(studentDao.findAll()).thenReturn(studentList);
        List<Student> serviceList = studentServiceImpl.findAll();
        assertEquals(studentList.size(), serviceList.size());

    }

    @Test
    void deleteById() {
        when(studentDao.findById(anyInt())).thenReturn(Optional.of(this.student));
        String result = studentServiceImpl.delete(this.student.getId());
        assertEquals("student deleted", result);
    }

    @Test
    void findById() {
        when(studentDao.findById(anyInt())).thenReturn(Optional.of(this.student));
        Student serviceStudent = studentServiceImpl.getById(this.student.getId());
        assertEquals(this.student.getName(), serviceStudent.getName());
    }

    @Test
    void findAllCoursesByStudentId() {
        when(studentDao.findById(anyInt())).thenReturn(Optional.of(student));
        when(coursesTakenDao.findAllCoursesByStudentId(anyInt())).thenReturn(student.getCourses());
        List<Course> course = coursesTakenDao.findAllCoursesByStudentId(student.getId());
        assertEquals(course.size(), student.getCourses().size());
        assertEquals(course.get(0).getName(),student.getCourses().get(0).getName());
        assertEquals(course.get(0).getName(),"Matematika");
        assertEquals("Matematika",student.getCourses().get(0).getName());
    }

    @Test
    void updateStudentsName() {
        when(studentDao.findById(anyInt())).thenReturn(Optional.of(student));
        student.setName("Ninica");
        when(studentDao.save(any())).thenReturn(student);
        Student updatedStudent = studentServiceImpl.updateStudentsName(anyInt(), student);
        assertEquals("Ninica", updatedStudent.getName());
    }
}