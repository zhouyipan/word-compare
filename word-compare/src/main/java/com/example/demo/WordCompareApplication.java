package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.draftable.NewComparison;

@SpringBootApplication
public class WordCompareApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordCompareApplication.class, args);
		NewComparison.compare("C:\\test\\3.docx", "C:\\test\\4.docx");
		
		
		
	}
}
