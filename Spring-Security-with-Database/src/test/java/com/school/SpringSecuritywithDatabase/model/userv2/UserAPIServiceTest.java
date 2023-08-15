package com.school.SpringSecuritywithDatabase.model.userv2;

import com.school.SpringSecuritywithDatabase.model.userApiGet.APIResponse;
import com.school.SpringSecuritywithDatabase.model.userApiGet.Name;
import com.school.SpringSecuritywithDatabase.model.userApiGet.UserAPI;
import com.school.SpringSecuritywithDatabase.model.userApiGet.UserAPIService;
import com.school.SpringSecuritywithDatabase.model.userApiPost.Address;
import com.school.SpringSecuritywithDatabase.model.userApiPost.UserApiPost;
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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        UserAPI userAPI = new UserAPI( "b", new Name("n", "m"));
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

    //int id, String name, String username, String email, Address address String street, String suite, String city, String zipCode
    @Test
    void addUser() {
        UserApiPost userApiPost = new UserApiPost(1,"Nemanja", "babadroga", "nemus@yahoo.com",
                new Address("140", "1", "Novi Sad", "21000")); //napravimo usera
        ResponseEntity<UserApiPost> response= new ResponseEntity<>(userApiPost, HttpStatus.CREATED); //napravimo objekt sa userom i statusom koji bi trebao da se vrati
        when(restTemplate.exchange(eq("https://jsonplaceholder.typicode.com/users"),
                eq(HttpMethod.POST),
                any(),
                eq(UserApiPost.class)))
                .thenReturn(response); // mokujemo proces

        UserApiPost serviceUser = userAPIService.addUser(userApiPost); //zovemo objekt koristeci servis
        assertEquals(response.getBody(), serviceUser); //uporedimo response sa objektom od servisa

        verify(restTemplate, times(1)).exchange(
                eq("https://jsonplaceholder.typicode.com/users"),
                eq(HttpMethod.POST),
                any(),
                eq(UserApiPost.class)
        );
    }
}