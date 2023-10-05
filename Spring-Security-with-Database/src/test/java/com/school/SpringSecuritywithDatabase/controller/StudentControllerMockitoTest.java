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
import org.springframework.http.HttpStatus;
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
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



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
        when(studentServiceImpl.create(any())).thenReturn(this.student);
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
        when(studentServiceImpl.create(any())).thenReturn(this.student);
        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(student)))
                .andExpect(status().isUnauthorized());
//                        .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("http://localhost/login")); THIS IS USED WHEN THERE IS A FORM_LOGIN WITH REDIRECTION TO LOGIN PAGE
    }
    @Test
    @WithMockUser(roles = "STUDENT")
    void deleteStudent() throws Exception {
        when(studentServiceImpl.delete(anyInt())).thenReturn("student deleted");
        MvcResult r = mockMvc.perform(delete("/student/{id}", student.getId())).andExpect(status().isOk()).andReturn();
        assertEquals("student deleted", r.getResponse().getContentAsString());
    }
    @Test
    @WithMockUser(roles = "STUDENT")
    void findById() throws Exception {
        when(studentServiceImpl.getById(anyInt())).thenReturn(this.student);
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
                .andExpect(content().contentType("application/octet-stream"))
                .andReturn();
        String csvContent = r.getResponse().getContentAsString();
        // Split the CSV content into rows
        String[] csvRows = csvContent.split("\n");
        // Verify the number of rows matches the expected number + 1 (for the header row)
        assertEquals(courseList.size() + 1, csvRows.length);
//         Verify each row matches the expected data
        for (int i = 1; i < csvRows.length; i++) {
            Course course = courseList.get(i - 1);
            String expectedRow = String.format("%d,%s", course.getId(), course.getName());
            assertEquals(expectedRow, csvRows[i].trim());
        }
    }

    @Test
    void updateStudentsName() {
    }
}