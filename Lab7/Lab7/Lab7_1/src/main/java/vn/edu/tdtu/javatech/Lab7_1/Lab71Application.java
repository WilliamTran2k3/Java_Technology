package vn.edu.tdtu.javatech.Lab7_1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab71Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Lab71Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Welcome to Java Tech World!");
	}
}
