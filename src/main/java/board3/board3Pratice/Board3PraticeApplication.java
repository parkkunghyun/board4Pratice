package board3.board3Pratice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Board3PraticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Board3PraticeApplication.class, args);
	}

}
