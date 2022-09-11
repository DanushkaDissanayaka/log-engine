package com.logger.config;

import com.logger.base.model.user.UserDto;
import com.logger.base.service.UserService;
import com.logger.repository.RoleRepository;
import com.logger.repository.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            RoleRepository repository,
            UserService userService
    ) {
     return args -> {
         Role admin = new Role(1L,"ADMIN");

         Role endUser = new Role(2L, "END_USER");

         repository.saveAll(
           List.of(admin, endUser)
         );

         UserDto user = new UserDto(
                 1L,
                 "Admin",
                 "admin@logger.live",
                 "123"
         );
         userService.save(user);
     };
    }
}
