package org.kata.springbootbootstrap.service;


import org.kata.springbootbootstrap.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl {
    private final RoleQuerySQL roleQuerySQL;

    @Autowired
    public RoleServiceImpl(RoleQuerySQL roleQuerySQL) {
        this.roleQuerySQL = roleQuerySQL;
    }


    public List<Role> getAllRoles() {
        return roleQuerySQL.getAllRoles();
    }


    public Role getRole(String userRole) {
        return roleQuerySQL.getRole(userRole);
    }


    public Role getRoleById(Long id) {
        return roleQuerySQL.getRoleById(id);
    }


    @Transactional
    public void addRole(Role role) {
        roleQuerySQL.addRole(role);
    }
}
