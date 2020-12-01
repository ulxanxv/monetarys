package ru.monetarys.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ru.monetarys.interceptors.HeaderRequestInterceptor;
import ru.monetarys.services.clientprofile.ApplicationProperties;

@Configuration
public class RestClientConfiguration {

    @Autowired
    private ApplicationProperties applicationProperties;

    private HttpClient httpClient() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(applicationProperties.getClientProfileService().getConnectionTimeout())
                .setSocketTimeout(applicationProperties.getClientProfileService().getReadTimeout())
                .build();

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .setMaxConnTotal(applicationProperties.getClientProfileService().getMaxConnectionTotal())
                .setMaxConnPerRoute(applicationProperties.getClientProfileService().getMaxConnectionPerRoute())
                .build();
    }

    private HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .requestFactory(this::clientHttpRequestFactory)
                .additionalInterceptors(new HeaderRequestInterceptor(applicationProperties.getClientProfileService().getHttpHeader(), applicationProperties.getClientProfileService().getSecret()))
                .build();
    }

}