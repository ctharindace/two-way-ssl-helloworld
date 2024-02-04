package com.chethiya.visa.helloworld.clients;

import com.chethiya.visa.helloworld.configs.HelloAPIProperties;
import com.chethiya.visa.helloworld.dto.HelloMessage;
import com.chethiya.visa.helloworld.exception.ServiceRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Chethiya
 *
 * Unit test for {@link HelloRestClient}
 *
 */
class HelloRestClientTest {

    @InjectMocks
    private HelloRestClient restClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private HelloAPIProperties apiProps;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getHelloMessage() throws ServiceRuntimeException {
        final String dummyUrl = "http://api.somedummyurl.com/helloworld";
        final String messageText = "helloworld";
        final Date date = new Date();
        HelloMessage helloMessage = new HelloMessage(messageText, date);
        ResponseEntity<HelloMessage> responseEntity = new ResponseEntity<>(helloMessage, HttpStatus.OK);
        Mockito.when(apiProps.getHelloServiceUrl()).thenReturn(dummyUrl);
        Mockito.when(restTemplate.getForEntity(dummyUrl, HelloMessage.class)).thenReturn(responseEntity);

        HelloMessage responseMessage = restClient.getHelloMessage();

        assertEquals(helloMessage, responseMessage);
    }
}