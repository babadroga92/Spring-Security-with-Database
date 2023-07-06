package com.school.SpringSecuritywithDatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc
class StudentControllerMockitoTest {

    private Student student;
    @MockBean
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        this.student = new Student("Nina Jojic", null, null);
    }

    @Test
    void addStudent() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteStudent() {
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
    void findAllCoursesByStudentId() {
    }

    @Test
    void updateStudentsName() {
    }
}