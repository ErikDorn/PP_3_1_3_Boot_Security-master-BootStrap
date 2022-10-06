package org.kata.springbootbootstrap.service;


import org.kata.springbootbootstrap.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }


    public Role getRole(String userRole) {
        return roleDAO.getRole(userRole);
    }


    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }


    @Transactional
    public void addRole(Role role) {
        roleDAO.addRole(role);
    }
}

