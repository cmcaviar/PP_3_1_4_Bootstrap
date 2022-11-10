package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;


import java.util.List;
import java.util.Set;

public interface UserService {
     Set<Role> findRolesByName(String roleName);

    void updateUser(User user);

    List<Role> listRoles();

    User getUserById(int id);

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUserById(int id);

    User findByUsername(String username);

}
