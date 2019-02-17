package org.sid;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.sid.dao.CategoryRepository;
import org.sid.dao.ProductRepository;
import org.sid.entities.Category;
import org.sid.entities.Product;
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
		return args->{
			categoryRepo.deleteAll();
			Stream.of("C1 Ordinateur","C2 Imprimantes")
			      .forEach(c->
			      categoryRepo.save(new Category(c.split(" ")[0],c.split(" ")[1],new ArrayList<>()))
			      );
			categoryRepo.findAll().forEach(System.out::println);
			Category c1 = categoryRepo.findById("C1").get();
			productRepo.deleteAll();
			Stream.of("P1","P2","P3","P4")
		      .forEach(p->
		      productRepo.save(new Product(null,p,Math.random()*1000,c1))
		      );
			Category c2 = categoryRepo.findById("C2").get();
			Stream.of("P5","P6")
		      .forEach(p->
		      productRepo.save(new Product(null,p,Math.random()*1000,c2))
		      );
			productRepo.findAll().forEach(p->System.out.print(p.toString()));
			
		};
	}

}
