package hr.tvz.gwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GeneriranjeWebDizajnaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneriranjeWebDizajnaApplication.class, args);
	}
	
}
