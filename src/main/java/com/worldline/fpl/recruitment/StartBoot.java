package com.worldline.fpl.recruitment;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application entry point
 * 
 * @author A525125
 *
 */
@SpringBootApplication
@Slf4j
public class StartBoot {

	public static void main(String[] args) {
		log.info("Start application ...");
		SpringApplication.run(StartBoot.class, args);
	}

}
