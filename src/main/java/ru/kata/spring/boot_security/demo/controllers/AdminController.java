package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;


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
        model.addAttribute("userCurrent", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("listRoles", userService.listRoles());
        model.addAttribute("newUser", new User());
        return "admintest";
    }


    @PutMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id, @RequestParam("roles") Set<Role> roles){
        userService.updateUser(user, roles, id);
        System.out.println(user.getUsername());
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user")User user, @RequestParam Set<Role> roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }
}






//    @GetMapping("/{id}")
//    public String getUser(@PathVariable("id") int id, Model model) {
//        model.addAttribute(userService.getUserById(id));
//        return "user";
 //   }


//    @GetMapping("/new")
//        public String getUserFormForCreate(@ModelAttribute("user") User user, Model model) {
//            model.addAttribute("roles", userService.listRoles());
//            return "new";
//    }


//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        model.addAttribute("roles", userService.listRoles());
//        return "edit";
//    }

