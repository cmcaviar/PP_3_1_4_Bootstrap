package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    void add(Role role);
    Role getRoleByName(String name);
    Set<Role> getRolesByName(Set<Role> roles);
    List<Role> listRoles();
}
