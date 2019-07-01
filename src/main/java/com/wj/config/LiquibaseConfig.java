package com.wj.config;


import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    //@Bean
    public SpringLiquibase Liquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:liquibase/master.xml");
        springLiquibase.setShouldRun(true);
        springLiquibase.setDropFirst(false);
        return springLiquibase;
    }
}
