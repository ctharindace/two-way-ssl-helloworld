package com.chethiya.visa.helloworld.configs;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * @author Chethiya
 *
 * Holds the configs required to configure the two-way ssl enabled RestTemplate.
 */
@Slf4j
@Configuration
public class HelloConfig {

    private final APISecurityProperties securityProperties;

    private final ConnectionProperties connectionProps;

    public HelloConfig(APISecurityProperties keystoreProperties, ConnectionProperties connectionProperties) {
        this.securityProperties = keystoreProperties;
        this.connectionProps = connectionProperties;
    }

    /**
     * Composes the two-way ssl configured RestTemplate
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate getTwoWaySSLRestTemplate() {
        RestTemplate restTemplate = new RestTemplateBuilder().basicAuthentication(securityProperties.getUsername(),
                securityProperties.getPassword()).build();
        try {
            log.debug("Initialising the keystore, {}", securityProperties.getKeystoreName());
            KeyStore keyStore = KeyStore.getInstance(securityProperties.getKeystoreType());
            ClassPathResource classPathResource = new ClassPathResource(securityProperties.getKeystoreName());
            InputStream inputStream = classPathResource.getInputStream();
            keyStore.load(inputStream, securityProperties.getKeystorePassword().toCharArray());

            log.debug("Initialising ssl context for, {}", securityProperties.getKeystoreName());
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .loadKeyMaterial(keyStore, securityProperties.getKeystorePassword().toCharArray()).build();

            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
                    .setMaxConnTotal(connectionProps.getMaxConnTotal())
                    .setMaxConnPerRoute(connectionProps.getMaxConnPerRoute()).build();

            log.debug("HTTP Request factory for, {}", securityProperties.getKeystoreName());
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            requestFactory.setReadTimeout(connectionProps.getReadTimeout());
            requestFactory.setConnectTimeout(connectionProps.getConnectionTimeout());

            restTemplate.setRequestFactory(requestFactory);
        } catch (Exception exception) {
            log.error("Error in creating the two way SSL enabled restTemplate ", exception);
        }
        return restTemplate;
    }
}
