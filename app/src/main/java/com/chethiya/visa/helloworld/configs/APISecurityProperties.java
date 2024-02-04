package com.chethiya.visa.helloworld.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Chethiya
 *
 * This holds the keystore security information and endpoint credentials
 */
@Getter
@Component
public class APISecurityProperties {

    @Value("${chethiya.visa.security.keystore.password}")
    private String keystorePassword;

    @Value("${chethiya.visa.security.keystore.name}")
    private String keystoreName;

    @Value("${chethiya.visa.security.keystore.type}")
    private String keystoreType;

    @Value("${chethiya.visa.security.api.userid}")
    private String username;

    @Value("${chethiya.visa.security.api.password}")
    private String password;
}
