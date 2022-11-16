package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        UserDetails user = userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("listRoles", userService.listRoles());
        return "admintest";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "user";
    }
    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
        public String getUserFormForCreate(@ModelAttribute("user") User user, Model model) {
            model.addAttribute("roles", userService.listRoles());
            return "new";
    }


//    @GetMapping("/{id}/edit")
//    public String edit(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        model.addAttribute("roles", userService.listRoles());
//        return "edit";
//    }
    @PutMapping("/edit")
    public String update(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
