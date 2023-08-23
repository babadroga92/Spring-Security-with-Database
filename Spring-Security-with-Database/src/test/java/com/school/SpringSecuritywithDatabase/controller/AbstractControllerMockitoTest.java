package com.school.SpringSecuritywithDatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public abstract class AbstractControllerMockitoTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
}
