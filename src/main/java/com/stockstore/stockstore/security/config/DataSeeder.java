package com.stockstore.stockstore.security.config;

import com.stockstore.stockstore.security.user.Enum.Role;
import com.stockstore.stockstore.security.user.model.User;
import com.stockstore.stockstore.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
public class DataSeeder {
    private final UserRepository userRepository;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "admin@admin.com";

            userRepository.findByEmail(adminEmail).ifPresentOrElse(
                    (existingAdmin) -> {
                        existingAdmin.setName("Admin");
                        existingAdmin.setLastname("Admin");
                        existingAdmin.setPhoneNumber("11111111");
                        existingAdmin.setPassword(passwordEncoder.encode("123456"));
                        existingAdmin.setRole(Role.ADMIN);

                        userRepository.save(existingAdmin);
                        System.out.println("DATOS DE ADMINISTRADOR ACTUALIZADOS AUTOMÁTICAMENTE");
                    },
                    () -> {
                        User admin = new User();
                        admin.setEmail(adminEmail);
                        admin.setName("Admin");
                        admin.setLastname("Admin");
                        admin.setPhoneNumber("11111111");
                        admin.setPassword(passwordEncoder.encode("1234"));
                        admin.setRole(Role.ADMIN);

                        userRepository.save(admin);
                        System.out.println("ADMINISTRADOR INICIAL CREADO");
                    }
            );
        };
    }
}
