package com.cwd.tg.gps.security;

import reactor.core.publisher.Mono;

public interface SecurityService {

    Mono<String> getUserToken(String requestId);
}
