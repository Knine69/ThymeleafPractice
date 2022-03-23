package com.jhuguet.ThymeleafCRUDApplication.Controller;

import com.jhuguet.ThymeleafCRUDApplication.Model.User;
import com.jhuguet.ThymeleafCRUDApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("usersList", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/showNewUserForm")
    public String viewNewUserForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id){
        userService.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user";
    }
}
