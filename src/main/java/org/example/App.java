package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Main application class for starting the Spring Boot application.
 */
@SpringBootApplication
public class App {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * The main method which serves as the entry point of the Spring Boot application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}