package org.devs.crm.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "org.devs.crm")
public class DaoConfig {

    @Bean
    @Primary
    public DataSource postgresqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("sherbek95.");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/crm");
        return dataSource;
    }

    @Bean
    public DataSource postgresqlDataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("sherbek95.");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/crm");
        return dataSource;
    }
}
