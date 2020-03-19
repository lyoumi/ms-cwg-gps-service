package com.cwd.tg.gps.client.impl;

import static java.lang.String.format;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import com.cwd.tg.gps.client.AuthWebClient;
import com.cwd.tg.gps.exception.ServiceNotAvailableException;
import com.cwd.tg.gps.security.ServiceUser;
import com.cwd.tg.gps.security.UserToken;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class AuthWebClientImpl implements AuthWebClient {
    private static final String SERVICE_NOT_AVAILABLE_EXCEPTION_MESSAGE = "Service {%s} currently is not available.";
    private static final String REQUEST_ID_HEADER_NAME = "request_id";
    private static final String AUTH_FORMAT = "%s/private/auth/generate/";

    @Value("${services.auth.instance.name}")
    private String authInstanceName;

    private final LoadBalancerClient loadBalancerClient;
    private final ServiceUser serviceUser;
    private final WebClient webClient;

    @Override
    public Mono<UserToken> generateUserToken(String requestId) {
        var coreBaseUrl = getAuthBaseUrl();

        String url = format(AUTH_FORMAT, coreBaseUrl);

        return webClient
                .post()
                .uri(url)
                .headers(getHttpHeaders(requestId))
                .body(fromObject(serviceUser))
                .retrieve()
                .bodyToMono(String.class)
                .map(UserToken::new)
                .retry(3, throwable -> true);
    }

    private Consumer<HttpHeaders> getHttpHeaders(String requestId) {
        return httpHeaders -> httpHeaders.add(REQUEST_ID_HEADER_NAME, requestId);
    }

    private String getAuthBaseUrl() {
        return Optional.ofNullable(loadBalancerClient.choose(authInstanceName))
                .map(ServiceInstance::getUri)
                .map(URI::toString)
                .orElseThrow(() ->
                        new ServiceNotAvailableException(
                                format(SERVICE_NOT_AVAILABLE_EXCEPTION_MESSAGE, authInstanceName)));
    }
}
