package com.cwd.tg.gps.client;

import com.cwd.tg.gps.security.UserToken;

import reactor.core.publisher.Mono;

public interface AuthWebClient {
    Mono<UserToken> generateUserToken(String requestId);
}
