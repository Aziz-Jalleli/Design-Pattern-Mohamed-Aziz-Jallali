package org.pi.prestatairewebservice.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.pi.prestatairewebservice.exception.IllegalArgumentExceptionMapper;
import org.pi.prestatairewebservice.resource.WebService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(WebService.class);
        register(IllegalArgumentExceptionMapper.class);
    }
}
