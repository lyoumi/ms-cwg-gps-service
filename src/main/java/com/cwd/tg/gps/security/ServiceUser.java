package com.cwd.tg.gps.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "service.user")
public class ServiceUser {
    private String username;
    private String password;
}
