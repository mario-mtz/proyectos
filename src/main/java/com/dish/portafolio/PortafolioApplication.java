package com.dish.portafolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * @version 1.0
 * @author mariomtz
 * Configuración de la aplicación
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.dish.portafolio")
@EnableJpaRepositories(basePackages = {"com.dish.portafolio.dao"} )
public class PortafolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortafolioApplication.class, args);
	}
        
        @Bean
        public SpringSecurityDialect securityDialect() {
            return new SpringSecurityDialect();
        }

}

