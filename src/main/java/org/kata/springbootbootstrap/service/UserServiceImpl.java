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

@Service
public class UserServiceImpl implements UserDetailsService {
    private final UserQuerySQL userQuerySQL;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public UserServiceImpl(UserQuerySQL userQuerySQL, PasswordEncoder passwordEncoder) {
        this.userQuerySQL = userQuerySQL;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userQuerySQL.saveUser(user);
    }


    @Transactional
    public void updateUser(User user) {

        if (!user.getPassword().equals(userQuerySQL.getUserById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userQuerySQL.updateUser(user);
    }


    @Transactional
    public void deleteUser(long id) {
        userQuerySQL.deleteUser(id);
    }


    public List<User> getAllUsers() {
        return userQuerySQL.getAllUsers();
    }


    public User getUserByLogin(String email) {
        return userQuerySQL.getUserByEmail(email);
    }


    public User getUserById(long id) {
        return userQuerySQL.getUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userQuerySQL.getUserByEmail(email);
    }
}
