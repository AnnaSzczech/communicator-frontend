package com.crud.communicator.client;

import com.crud.communicator.domain.EmailValidatorDto;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


public class EmailValidator {
    private String endpoint = "http://localhost:8080/v1/emailValidator/";
    private RestTemplate restTemplate = new RestTemplate();

    public EmailValidatorDto verifyEmail(String email){
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint + email)
                .build().encode().toUri();
        System.out.println(url);
         return restTemplate.getForObject(url, EmailValidatorDto.class);
    }
}
