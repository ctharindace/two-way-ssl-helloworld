package com.chethiya.visa.helloworld.controllers;

import com.chethiya.visa.helloworld.clients.HelloRestClient;
import com.chethiya.visa.helloworld.dto.HelloMessage;
import com.chethiya.visa.helloworld.exception.ServiceRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chethiya
 *
 * Rest controller for the helloworld service
 */
@Slf4j
@RestController
public class HelloController {

    private final HelloRestClient helloClient;

    public HelloController(HelloRestClient helloClient) {
        this.helloClient = helloClient;
    }

    /**
     * Endpoint for the helloworld REST service
     * @return The response
     * @throws ServiceRuntimeException wrapped error from the client service call
     */
    @GetMapping(path = "/helloworld")
    public HelloMessage getHelloMessage() throws ServiceRuntimeException {
        log.debug("API call received at getHelloMessage()");
        return helloClient.getHelloMessage();
    }

}
