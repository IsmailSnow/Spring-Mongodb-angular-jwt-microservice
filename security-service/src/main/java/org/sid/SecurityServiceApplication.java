package org.sid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}
//	
//    @Bean
//    CommandLineRunner start(AccountService accountService){
//        return args->{
//            accountService.save(new AppRole(null,"USER"));
//            accountService.save(new AppRole(null,AppConstant.ADMIN));
//            Stream.of("user1","user2","user3","admin").forEach(un->{
//                accountService.saveUser(un,"1234","1234");
//            });
//            accountService.addRoleToUser("admin",AppConstant.ADMIN);
//        };
//    }
//    @Bean
//    BCryptPasswordEncoder getBCPE(){
//        return new BCryptPasswordEncoder();
//    }

}
