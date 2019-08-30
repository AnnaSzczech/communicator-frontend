package com.crud.communicator.client;

import com.crud.communicator.domain.AccountDto;
import com.crud.communicator.domain.LoginDto;
import com.crud.communicator.domain.UserDto;
import com.vaadin.flow.component.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public class AccountClient {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountClient.class);
    private String endpoint = "http://localhost:8080/v1/accounts/";
    private RestTemplate restTemplate = new RestTemplate();

    public void logIn(LoginDto login) throws HttpClientErrorException {
            URI url = UriComponentsBuilder.fromHttpUrl(endpoint + "logIn/" + login.getLogin() + "&" + login.getPassword())
                    .build().encode().toUri();
            restTemplate.put(url, LoginDto.class);
    }

    public void logOut(String login) throws HttpClientErrorException {
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint + "logOut/" + login)
                .build().encode().toUri();
        restTemplate.put(url, LoginDto.class);
    }

    public void createNewAccount(AccountDto account) throws HttpClientErrorException {
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint)
                .build().encode().toUri();
        restTemplate.postForObject(url, account, AccountDto.class);
    }

    public void deleteTheAccount(String login) {
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint + login)
                .build().encode().toUri();
        restTemplate.delete(url);
    }

    public List<UserDto> getUsers(String logger) {
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint)
                .build().encode().toUri();
        try {
            UserDto[] usersResponse = restTemplate.getForObject(url, UserDto[].class);
            return Arrays.asList(ofNullable(usersResponse).orElse(new UserDto[0])).stream()
                    .filter(user -> !user.getLogin().equals(logger))
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            showMessage("Empty List");
            return new ArrayList<>();
        }
    }

    public void showMessage(String message){
        AccountClient.LOGGER.error(message);
        Notification.show(message).setPosition(Notification.Position.MIDDLE);
    }
}
