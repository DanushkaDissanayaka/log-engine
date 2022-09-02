package com.logger.config;

import com.logger.repository.RoleRepository;
import com.logger.repository.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            RoleRepository repository
    ) {
     return args -> {
         Role admin = new Role(1L,"ADMIN");

         Role endUser = new Role(2L, "END_USER");

         repository.saveAll(
           List.of(admin, endUser)
         );
     };
    }
}
