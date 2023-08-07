package com.school.SpringSecuritywithDatabase.model.userv2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserAPIServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    UserAPIService userAPIService;


    @Test
    void getRandomUsers() {
        UserAPI userAPI = new UserAPI(1, "b", new Name("n", "m"));
        APIResponse apiResponse = new APIResponse(Collections.singletonList(userAPI));
        ResponseEntity<APIResponse> response = new ResponseEntity<>(apiResponse,
                null, HttpStatus.OK);
        when(restTemplate.exchange("https://randomuser.me/api/?results=1",
                HttpMethod.GET, null, new ParameterizedTypeReference<APIResponse>() {
                })).thenReturn(response);
       List<UserAPI> serviceGetAll = userAPIService.getRandomUsers(1);
       assertNotNull(serviceGetAll);
       assertEquals(response.getBody().getResults().size(), serviceGetAll.size());
       verify(restTemplate, times(1)).exchange("https://randomuser.me/api/?results=1",
               HttpMethod.GET, null, new ParameterizedTypeReference<APIResponse>() {
               });
    }
    @Test
    void addUser() {
        UserAPI userAPI = new UserAPI(1, "male", new Name("nemanja", "milanovic")); //napravimo usera
        ResponseEntity<UserAPI> response= new ResponseEntity<>(userAPI, HttpStatus.CREATED); //napravimo objekt sa userom i statusom koji bi trebao da se vrati
        when(restTemplate.exchange(eq("https://jsonplaceholder.typicode.com/users"),
                eq(HttpMethod.POST),
                any(),
                eq(UserAPI.class)))
                .thenReturn(response); // mokujemo proces

        UserAPI serviceUser = userAPIService.addUser(userAPI); //zovemo objekt koristeci servis
        assertEquals(response.getBody(), serviceUser); //uporedimo response sa objektom od servisa

        verify(restTemplate, times(1)).exchange(
                eq("https://jsonplaceholder.typicode.com/users"),
                eq(HttpMethod.POST),
                any(),
                eq(UserAPI.class)
        );
    }
}