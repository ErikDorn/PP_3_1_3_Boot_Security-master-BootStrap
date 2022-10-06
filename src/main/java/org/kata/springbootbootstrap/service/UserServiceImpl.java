package org.kata.springbootbootstrap.service;

import org.kata.springbootbootstrap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final RoleDAO roleDAO;

    @Autowired
    @Lazy
    public UserServiceImpl(RoleDAO roleDAO, UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.roleDAO = roleDAO;
    }


    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }


    @Transactional
    public void updateUser(User user) {

        if (!user.getPassword().equals(userDAO.getUserById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.updateUser(user);
    }


    @Transactional
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }


    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }


    public User getUserByLogin(String email) {
        return userDAO.getUserByEmail(email);
    }


    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDAO.getUserByEmail(email);
    }

    public void getUserRoles(User user) {
        user.setRoles(user.getRoles().stream()
                .map(role -> roleDAO.getRole(role.getName()))
                .collect(Collectors.toSet()));
    }
}
