package com.chethiya.visa.helloworld.controllers;

import com.chethiya.visa.helloworld.clients.HelloRestClient;
import com.chethiya.visa.helloworld.dto.HelloMessage;
import com.chethiya.visa.helloworld.exception.ServiceRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {
    @InjectMocks
    private HelloController helloController;

    @Mock
    private HelloRestClient restClient;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getHelloMessage() throws ServiceRuntimeException {
        final String messageText = "helloworld";
        final Date date = new Date();
        HelloMessage expectedMessage = new HelloMessage(messageText, date);
        Mockito.when(restClient.getHelloMessage()).thenReturn(expectedMessage);
        HelloMessage actualMessage = helloController.getHelloMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getHelloMessageException() throws ServiceRuntimeException {
        final String errorMessage = "Error message";
        Mockito.when(restClient.getHelloMessage()).thenThrow(new ServiceRuntimeException(errorMessage));
        Exception exception = Assertions.assertThrowsExactly(ServiceRuntimeException.class,
                () -> helloController.getHelloMessage());
        assertEquals(errorMessage, exception.getMessage());
    }
}