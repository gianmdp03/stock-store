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
            // Verificamos si ya existe el admin para no duplicarlo cada vez que inicias
            if (userRepository.findByRole(Role.ADMIN).isEmpty()) {
                User admin = new User();
                admin.setName("Admin");
                admin.setLastname("Admin");
                admin.setEmail("admin@admin.com");
                admin.setPassword(passwordEncoder.encode("1234")); // Contraseña inicial
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);
                System.out.println("ADMINISTRADOR INICIAL CREADO");
            }
        };
    }
}
