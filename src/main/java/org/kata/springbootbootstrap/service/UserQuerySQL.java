package org.kata.springbootbootstrap.service;

import org.kata.springbootbootstrap.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserQuerySQL {
    @PersistenceContext
    private EntityManager entityManager;


    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public void deleteUser(long id) {
        User someUser = entityManager.find(User.class, id);
        entityManager.remove(someUser);
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }


    public User getUserByEmail(String email) {
        return entityManager.createQuery("select u from User u where u.email =: email", User.class)
                .setParameter("email", email).getSingleResult();
    }


    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
}

