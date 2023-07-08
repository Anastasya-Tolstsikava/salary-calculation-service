package com.tolstsikava.testproject.demo.client.config;

import com.tolstsikava.testproject.demo.client.EmployeeWorkingTimeInDayClient;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    @Bean
    @Primary
    public WebClient petWebClient() {
        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://test-service.free.beeceptor.com")
                .build();
    }

    @Bean
    public EmployeeWorkingTimeInDayClient employeeWorkingHoursClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(
                WebClientAdapter.forClient(petWebClient())).build();

        return httpServiceProxyFactory.createClient(EmployeeWorkingTimeInDayClient.class);
    }
}
