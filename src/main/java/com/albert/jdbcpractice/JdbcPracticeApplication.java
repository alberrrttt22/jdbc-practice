package com.albert.jdbcpractice;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class JdbcPracticeApplication implements CommandLineRunner {

	private final JdbcTemplate jdbcTemplate;

	public JdbcPracticeApplication(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	public static void main(String[] args) {
		SpringApplication.run(JdbcPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Datasource: " + jdbcTemplate.getDataSource());
		jdbcTemplate.execute("Select 1");
	}
}
