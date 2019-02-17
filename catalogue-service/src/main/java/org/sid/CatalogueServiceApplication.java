package org.sid;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.sid.dao.CategoryRepository;
import org.sid.dao.ProductRepository;
import org.sid.entities.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatalogueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogueServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CategoryRepository categoryRepo , ProductRepository productRepo) {
		categoryRepo.deleteAll();
		return args->{
			Stream.of("Ordinateur","Imprimantes")
			      .forEach(c->
			      categoryRepo.save(new Category("C1",c,new ArrayList<>()))
			      );
			categoryRepo.findAll().forEach(System.out::println);
		};
	}

}
