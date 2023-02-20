package org.devs.api.config;

import org.devs.business.config.BusinessConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScans({
        @ComponentScan("org.devs.api"),
        @ComponentScan(basePackageClasses = BusinessConfig.class)
})
@EnableWebMvc
public class ApiConfig {

}