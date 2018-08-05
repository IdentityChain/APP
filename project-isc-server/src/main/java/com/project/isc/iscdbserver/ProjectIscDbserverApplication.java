package com.project.isc.iscdbserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectIscDbserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectIscDbserverApplication.class, args);
	}
}
