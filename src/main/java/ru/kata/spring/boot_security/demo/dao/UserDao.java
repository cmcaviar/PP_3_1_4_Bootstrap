package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User show(int id);
    void save(User user);
    void update(User user);
    void delete(int id);
    User findUserByUsername(String username) throws UsernameNotFoundException;
}
