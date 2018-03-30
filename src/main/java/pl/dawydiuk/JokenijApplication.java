package pl.dawydiuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("pl.dawydiuk")
public class JokenijApplication {

	public static void main(String[] args) {
		SpringApplication.run(JokenijApplication.class, args);
	}
}
