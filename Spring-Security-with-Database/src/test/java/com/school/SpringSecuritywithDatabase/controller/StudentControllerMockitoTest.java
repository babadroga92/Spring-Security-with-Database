package com.school.SpringSecuritywithDatabase.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentController.class)
class StudentControllerMockitoTest extends AbstractControllerMockitoTest{

    private Student student;
    @MockBean
    private StudentServiceImpl studentServiceImpl;

    @BeforeEach
    void setUp() {
        this.student = new Student("Nina Jojic",null, null);
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void addStudent() throws Exception {
        when(studentServiceImpl.addStudent(any())).thenReturn(this.student);
        MvcResult r = mockMvc.perform(post("/student").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(student))).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nina Jojic"))
                .andReturn();
        Student s1 = objectMapper.readValue(r.getResponse().getContentAsByteArray(), Student.class);
        assertEquals(this.student.getName(), s1.getName());
    }

    @Test
    @WithAnonymousUser
    void addStudentWithoutUser() throws Exception {
        when(studentServiceImpl.addStudent(any())).thenReturn(this.student);
        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(student)))
                        .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void findAll() throws Exception {
//        List<Student> studentList = new ArrayList<>();
//        studentList.add(student);
//        when(studentServiceImpl.findAll()).thenReturn(studentList);
//        MvcResult r = mockMvc.perform(get("/student/list")).andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(studentList.get(0).getName())))
//                .andReturn();
//        List<Student> studentListResponse = objectMapper.readValue(r.getResponse().getContentAsByteArray(), List.class);
//        assertNotNull(studentListResponse);
//        assertEquals(studentList.size(), studentListResponse.size());
//    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void deleteStudent() throws Exception {
        when(studentServiceImpl.deleteById(anyInt())).thenReturn("student deleted");
        MvcResult r = mockMvc.perform(delete("/student/{id}", student.getId())).andExpect(status().isOk()).andReturn();
        assertEquals("student deleted", r.getResponse().getContentAsString());
    }


    @Test
    @WithMockUser(roles = "STUDENT")
    void findById() throws Exception {
        when(studentServiceImpl.findById(anyInt())).thenReturn(this.student);
        MvcResult r = mockMvc.perform(get("/student/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(student.getName())))
                .andReturn();
        Student s1 = objectMapper.readValue(r.getResponse().getContentAsByteArray(), Student.class);
        assertEquals(student.getName(), s1.getName());
    }



    @Test
    @WithMockUser(roles = "STUDENT")
    void findAllCoursesByStudentId() throws Exception {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Matematika", null, null));
        courseList.add(new Course("Programiranje", null, null));
        when(studentServiceImpl.findAllCoursesByStudentId(anyInt())).thenReturn(courseList);
        MvcResult r = mockMvc.perform(get("/student/{id}/listOfCourses", anyInt()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(courseList.get(0).getName())).andReturn();
        List<Course> objectMapperList = objectMapper.readValue(r.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });
        assertNotNull(objectMapperList);
        assertEquals(courseList.size(), objectMapperList.size());
        assertEquals(courseList.get(0).getName(), objectMapperList.get(0).getName());
    }

    @Test
    void updateStudentsName() {
    }
}