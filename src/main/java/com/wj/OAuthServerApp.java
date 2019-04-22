package com.wj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jun.wang
 * @title: OAuthServerApp
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/2 9:15
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, LiquibaseAutoConfiguration.class})
@EnableEurekaClient
public class OAuthServerApp {

    public static void main(String args[]) {
        SpringApplication.run(OAuthServerApp.class, args);
    }
}
