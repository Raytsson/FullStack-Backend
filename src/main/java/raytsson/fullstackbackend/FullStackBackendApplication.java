package raytsson.fullstackbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FullStackBackendApplication {



	public static void main(String[] args) {
		SpringApplication.run(FullStackBackendApplication.class, args);

		System.out.println("MYSQLHOST: " + System.getenv("MYSQLHOST"));
		System.out.println("MYSQLPORT: " + System.getenv("MYSQLPORT"));
		System.out.println("MYSQLDATABASE: " + System.getenv("MYSQLDATABASE"));
		System.out.println("MYSQLUSER: " + System.getenv("MYSQLUSER"));
		System.out.println("MYSQLPASSWORD: " + System.getenv("MYSQLPASSWORD"));
	}

}
