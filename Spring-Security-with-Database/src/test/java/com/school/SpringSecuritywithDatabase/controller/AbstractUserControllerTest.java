package com.school.SpringSecuritywithDatabase.controller;
import org.junit.jupiter.api.Test;
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
        mockMvc.perform(get("/user/listOfUsersByNumber")
                        .param("limit", "1"))
                .andExpect(status().isOk());
    }
}