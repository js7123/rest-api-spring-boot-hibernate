package com.js.springboothibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootHibernateApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootHibernateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHibernateApplication.class, args);
    }

}
