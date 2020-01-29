package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// test CI 3

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		try {
			SpringApplication.run(Application.class, args);
			System.out.println("Application demarree !");
		} catch (Exception e) {
			System.out.println("Application demarree !");
		}
	}
}
