package com.albert.springbootbackend;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Log
public class SpringBootPracticeApplication implements CommandLineRunner {

	private final JdbcTemplate jdbcTemplate;

	public SpringBootPracticeApplication(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Datasource: " + jdbcTemplate.getDataSource());
		jdbcTemplate.execute("Select 1");
	}
}
