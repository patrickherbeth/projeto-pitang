package br.com.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class HibernateConfig {
  @Bean
  public Module hibernate5Module() {
    return new Hibernate5Module();
  }
}
