package org.kata.springbootbootstrap;

import org.kata.springbootbootstrap.model.User;
import org.kata.springbootbootstrap.service.RoleServiceImpl;
import org.kata.springbootbootstrap.service.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.kata.springbootbootstrap.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public Init(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);

        User newUser = new User("User", "Userov", 23, "user@mail.ru",
                "user", userSet);
        User admin = new User("Admin", "Adminov", 30, "admin@mail.ru",
                "admin", adminSet);

        userService.saveUser(newUser);
        userService.saveUser(admin);
    }
}
