package com.jhuguet.ThymeleafCRUDApplication.Controller;

import com.jhuguet.ThymeleafCRUDApplication.Model.User;
import com.jhuguet.ThymeleafCRUDApplication.Service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;


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

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/updateUser/{user}", method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public String updateUser(@PathVariable(value = "user") User user){
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user";
    }
}
