package org.sid.conf.beans;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class Beans {
	
  @Bean
  BCryptPasswordEncoder getBCPE(){
      return new BCryptPasswordEncoder();
  }
  
  @Bean
  public LocalValidatorFactoryBean validator(MessageSource messageSource) {
      LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
      validatorFactoryBean.setValidationMessageSource(messageSource);
      return validatorFactoryBean;
  }

}
