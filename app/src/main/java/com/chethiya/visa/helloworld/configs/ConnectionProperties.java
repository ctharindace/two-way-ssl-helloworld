package com.chethiya.visa.helloworld.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Chethiya
 *
 * This holds the connection properties of a RestTemplate
 */
@Getter
@Component
public class ConnectionProperties {

    @Value("${chethiya.visa.connection.timeout}")
    private int connectionTimeout;

    @Value("${chethiya.visa.connection.read.timeout}")
    private int readTimeout;

    @Value("${chethiya.visa.connection.max.total}")
    private int maxConnTotal;

    @Value("${chethiya.visa.connection.max.route.total}")
    private int maxConnPerRoute;

}
