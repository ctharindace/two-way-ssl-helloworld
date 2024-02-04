package com.chethiya.visa.helloworld.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Chethiya
 *
 * This holds the API properties
 */
@Getter
@Component
public class HelloAPIProperties {

    @Value("${chethiya.visa.service.url.hello}")
    private String helloServiceUrl;

}
