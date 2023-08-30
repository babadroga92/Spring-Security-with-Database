package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class AbstractUserControllerTest extends AbstractControllerMockitoTest {


    @Test
    void updatePassword() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void numberOfUsers() throws Exception {
        mockMvc.perform(get("/listOfUsersByNumber")
                        .param("limit", "2"))
                .andExpect(status().isOk());
    }
}