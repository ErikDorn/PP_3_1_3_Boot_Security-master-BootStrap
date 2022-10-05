package org.kata.springbootbootstrap.service;

import org.kata.springbootbootstrap.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class RoleQuerySQL {
    @PersistenceContext
    private EntityManager entityManager;


    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }


    public Role getRole(String name) {
        return entityManager.createQuery("select r from Role r where r.name =: name", Role.class)
                .setParameter("name", name).getSingleResult();
    }


    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }


    public void addRole(Role role) {
        entityManager.persist(role);
    }
}

