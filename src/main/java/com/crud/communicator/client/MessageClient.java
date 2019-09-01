package com.crud.communicator.client;

import com.crud.communicator.domain.AccountDto;
import com.crud.communicator.domain.MessageDto;
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

public class MessageClient {
    private static Logger LOGGER = LoggerFactory.getLogger(AccountClient.class);
    private String endpoint = "http://localhost:8080/v1/messages/";
    private RestTemplate restTemplate = new RestTemplate();

    public List<MessageDto> getConversation(String sender, String recipient) throws HttpClientErrorException {
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint + sender + "&" + recipient)
                .build().encode().toUri();
        try {
            MessageDto[] usersResponse = restTemplate.getForObject(url, MessageDto[].class);
            return Arrays.asList(ofNullable(usersResponse).orElse(new MessageDto[0]));
        } catch (RestClientException e) {
            showMessage("Empty List");
            return new ArrayList<>();
        }
    }

    public void createMessage(MessageDto message) throws HttpClientErrorException {
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint)
                .build().encode().toUri();
        restTemplate.postForObject(url, message, MessageDto.class);
    }

    public void showMessage(String message){
        LOGGER.error(message);
        Notification.show(message).setPosition(Notification.Position.MIDDLE);
    }
}
