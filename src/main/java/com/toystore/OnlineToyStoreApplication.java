package com.toystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnlineToyStoreApplication extends SpringBootServletInitializer {

    // Extends SpringBootServletInitializer so it also works when
    // deployed as a WAR file to an external Tomcat server.
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OnlineToyStoreApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineToyStoreApplication.class, args);
    }
}
