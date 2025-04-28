package com.devtiro.dao_jdbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    // Spring automatically injects (passes) the DataSource (the database connection)
    // into jdbcTemplate method
    //It returns a ready-to-use JdbcTemplate object — connected to the database
    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
