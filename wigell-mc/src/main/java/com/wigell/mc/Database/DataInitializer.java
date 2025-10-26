package com.wigell.mc.Database;

import com.wigell.mc.DAO.UserDAO;
import com.wigell.mc.Entity.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userDAO.findByUsername("admin") == null) {
                UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                userDAO.save(admin);
                System.out.println("✅ Created admin user");
            }

            if (userDAO.findByUsername("user") == null) {
                UserEntity member = new UserEntity();
                member.setUsername("user");
                member.setPassword(passwordEncoder.encode("user123"));
                member.setRole("USER");
                userDAO.save(member);
                System.out.println("✅ Created member user");
            }
        };
    }
}