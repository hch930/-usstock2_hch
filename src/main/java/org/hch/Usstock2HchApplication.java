package org.hch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.hch.repository")
public class Usstock2HchApplication {

	public static void main(String[] args) {
		SpringApplication.run(Usstock2HchApplication.class, args);
	}

}
