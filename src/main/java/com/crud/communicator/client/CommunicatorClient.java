package com.crud.communicator.client;

import com.crud.communicator.domain.LoginDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class CommunicatorClient {

    public static Logger LOGGER = LoggerFactory.getLogger(CommunicatorClient.class);
    private String endpoint = "http://localhost:8080/v1/accounts";
    private RestTemplate restTemplate = new RestTemplate();

    public void logIn(LoginDto login) throws HttpClientErrorException {
            URI uri = UriComponentsBuilder.fromHttpUrl(endpoint + "/logIn/" + login.getLogin() + "&" + login.getPassword())
                    .build().encode().toUri();
            restTemplate.put(uri, LoginDto.class);
    }

    public void logOut(String login) throws HttpClientErrorException {
        URI uri = UriComponentsBuilder.fromHttpUrl(endpoint + "/logOut/" + login)
                .build().encode().toUri();
        restTemplate.put(uri, LoginDto.class);
    }
}
