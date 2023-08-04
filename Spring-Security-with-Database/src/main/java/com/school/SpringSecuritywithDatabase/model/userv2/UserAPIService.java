package com.school.SpringSecuritywithDatabase.model.userv2;

import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserAPIService {
    public static final String EXTERNAL_API_URL ="https://randomuser.me/api/";
    @Autowired
    private RestTemplate restTemplate;

    public UserAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserAPI> getRandomUsers(int x) throws WrongIdException{
        ResponseEntity<APIResponse> response = restTemplate
                .exchange(EXTERNAL_API_URL + "?results=" + x,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<APIResponse>() {
                        });
        if(response.getStatusCode() == HttpStatus.OK){
            return  response.getBody().results;
        }else {
            throw new  WrongIdException("Nebitno");
        }
    }
}
