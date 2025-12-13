package com.example.schedulingtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main Spring Boot application class for the scheduling example.
 *
 * <p>Starts the application and turns on Spring's scheduling support,
 * so methods annotated with {@code @Scheduled} can run on a timer.</p>
 */
@SpringBootApplication
@EnableScheduling
public class SchedulingTasksApplication {

	/**
	 * Application entry point.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SchedulingTasksApplication.class, args);
	}

}