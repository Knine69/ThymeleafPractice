package com.jhuguet.ThymeleafCRUDApplication.Controller;

import com.jhuguet.ThymeleafCRUDApplication.Model.User;
import com.jhuguet.ThymeleafCRUDApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class UserController {

    private static List<User> userList = new ArrayList<>();

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, model, "firstName", "asc", "");
    }


    @GetMapping("/showNewUserForm")
    public String viewNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        user.setFirstName(user.getFirstName().trim());
        user.setLastName(user.getLastName().trim());
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model,
                                @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir,
                                @RequestParam("filterBy") String filterBy) {
        int pageSize = 4;
        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        userList = page.getContent() ;

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("usersList", filterBy.isEmpty() ? userList : filterUsers(filterBy));
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
        model.addAttribute("filterBy", filterBy);

        return "index";
    }

    public List<User> filterUsers(String filter) {
        return userList.stream()
                .filter(x -> x.getLastName().trim().equalsIgnoreCase(filter) ||
                        x.getFirstName().trim().equalsIgnoreCase(filter))
                .collect(Collectors.toList());
    }
}
