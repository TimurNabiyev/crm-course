package org.devs.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScans({
        @ComponentScan("org.devs.api")
})
@EnableWebMvc
public class ApiConfig {
}