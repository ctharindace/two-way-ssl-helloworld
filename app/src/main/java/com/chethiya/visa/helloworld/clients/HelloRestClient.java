package com.chethiya.visa.helloworld.clients;

import com.chethiya.visa.helloworld.configs.HelloAPIProperties;
import com.chethiya.visa.helloworld.dto.HelloMessage;
import com.chethiya.visa.helloworld.exception.ServiceRuntimeException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.chethiya.visa.helloworld.util.JsonUtil.toJson;

/**
 * @author Chethiya
 *
 * REST client for calling the remote helloworld service
 */
@Slf4j
@Component
public class HelloRestClient {

    private final RestTemplate restTemplate;

    private final HelloAPIProperties apiProps;

    public HelloRestClient(RestTemplate restTemplate, HelloAPIProperties apiProps) {
        this.restTemplate = restTemplate;
        this.apiProps = apiProps;
    }
    /**
     * This calls the remote service
     * <a href="https://sandbox.api.visa.com/vdp/helloworld">Visa Hello World microservice endpoint</a>
     * and returns the response
     *
     * @return Response from the service
     * @throws ServiceRuntimeException Wrapped service exception
     */
    public HelloMessage getHelloMessage() throws ServiceRuntimeException {
        ResponseEntity<HelloMessage> responseEntity;
        try {
            log.debug("Calling the remote service, {}", apiProps.getHelloServiceUrl());
            responseEntity = restTemplate.getForEntity(apiProps.getHelloServiceUrl(), HelloMessage.class);
            log.info("Remote service call success. Response: {}", toJson(responseEntity.getBody()));
        } catch (RestClientException ex) {
           throw new ServiceRuntimeException("Error in calling the service.", ex);
        }
        return responseEntity.getBody();
    }

}
