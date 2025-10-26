package com.wigell.mc.Database;

import com.wigell.mc.DAO.UserDAO;
import com.wigell.mc.Entity.UserEntity;
import com.wigell.mc.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    private final UserDAO userDAO;
    private final UserService userService;

    public DatabaseInitializer(UserDAO userDAO, UserService userService) {
        this.userDAO = userDAO;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        UserEntity admin = userDAO.findByUsername("admin");
        if (admin == null) {
            userService.register("admin", "admin123", "ADMIN");
            System.out.println("created admin");
        }

        UserEntity user = userDAO.findByUsername("user");
        if (user == null) {
            userService.register("user", "user123", "USER");
            System.out.println("created user");
        }
    }


}
