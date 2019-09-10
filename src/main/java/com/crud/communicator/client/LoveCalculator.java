package com.crud.communicator.client;

import com.crud.communicator.domain.LoveCalculatorDto;
import com.vaadin.flow.component.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import static java.util.Optional.ofNullable;

public class LoveCalculator {
    private static Logger LOGGER = LoggerFactory.getLogger(AccountClient.class);
    private String endpoint = "http://localhost:8080/v1/calculator";
    private RestTemplate restTemplate = new RestTemplate();

    public LoveCalculatorDto getPercentage(String fname, String sname){
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("fname", fname)
                .queryParam("sname", sname)
                .build().encode().toUri();
        try {
            LoveCalculatorDto loveCalculatorDto = restTemplate.getForObject(url, LoveCalculatorDto.class);
            return ofNullable(loveCalculatorDto).orElse(createEmptyLoveCalculator());
        } catch (RestClientException e) {
            loggerError("Bad request");
            return createEmptyLoveCalculator();
        }
    }

    private LoveCalculatorDto createEmptyLoveCalculator(){
        LoveCalculatorDto empty = new LoveCalculatorDto();
        empty.setPercentage("");
        empty.setResult("TRY AGAIN");
        return empty;
    }

    private void loggerError(String message){
        LOGGER.error(message);
    }
}
