package org.devs.business.config;

import org.devs.data.dao.config.DaoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({
        @ComponentScan("org.devs.business"),
        @ComponentScan(basePackageClasses = DaoConfig.class)
})
public class BusinessConfig {

}
