package org.devs.business.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({
        @ComponentScan("org.devs.crm"),
        @ComponentScan("org.devs.business")
})
public class BusinessConfig {
}
