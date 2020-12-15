package com.docker.institute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class DockerRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerRestAppApplication.class, args);
	}

}
