package com.school.SpringSecuritywithDatabase.model.userApiGet;

import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.userApiPost.UserApiPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserAPIService {
    public static final String EXTERNAL_API_URL ="https://randomuser.me/api/";

    public static final String EXTERNAL_API_POST ="https://jsonplaceholder.typicode.com/users";

    private RestTemplate restGetTemplate;

    private RestTemplate restPostTemplate;
@Autowired
    public UserAPIService(@Qualifier("userGetRestTemplate") RestTemplate restGetTemplate,
                          @Qualifier("userPostRestTemplate") RestTemplate restPostTemplate) {
        this.restGetTemplate = restGetTemplate;
        this.restPostTemplate = restPostTemplate;
    }

    public List<UserAPI> getRandomUsers(int x) throws WrongIdException{
        ResponseEntity<APIResponse> response = restGetTemplate
                .exchange(EXTERNAL_API_URL + "?results=" + x,
                        HttpMethod.GET,
                        null, //httpEntity za post metodu
                        new ParameterizedTypeReference<APIResponse>() {
                        });
        if(response.getStatusCode() == HttpStatus.OK){
            return  response.getBody().results;
        }else {
            throw new  WrongIdException("Nebitno");
        }
    }

    public UserApiPost addUser(UserApiPost userApiPost)throws WrongIdException{
        HttpHeaders httpHeaders = new HttpHeaders();//format podataka ce biti json
        httpHeaders.setContentType(MediaType.APPLICATION_JSON); // ovde setujemo tip podataka
        HttpEntity<UserApiPost> user = new HttpEntity<>(userApiPost, httpHeaders); //treci parametar u response entiti metodi
        ResponseEntity<UserApiPost> response = restPostTemplate
                .exchange(EXTERNAL_API_POST, HttpMethod.POST, user, UserApiPost.class); // ovo je kao send dugme
        if(response.getStatusCode() == HttpStatus.CREATED){
            return response.getBody(); //ako taj user postoji nakon "send", json telo se vrati
        }else {
            throw new WrongIdException("Nebitno");
        }
    }
}
