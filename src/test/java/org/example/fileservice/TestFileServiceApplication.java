package org.example.fileservice;

import org.springframework.boot.SpringApplication;
import org.testcontainers.utility.TestcontainersConfiguration;

public class TestFileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(FileServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
