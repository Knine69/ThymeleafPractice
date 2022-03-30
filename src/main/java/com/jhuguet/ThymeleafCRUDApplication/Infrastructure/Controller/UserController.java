package com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Controller;

import com.jhuguet.ThymeleafCRUDApplication.Application.Dto.UserDto;
import com.jhuguet.ThymeleafCRUDApplication.Application.Handlers.FilterUsersHandler;
import com.jhuguet.ThymeleafCRUDApplication.Domain.Model.User;
import com.jhuguet.ThymeleafCRUDApplication.Domain.repository.UserService;
import com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, model, "firstName", "asc", "");
    }


    @GetMapping("/showNewUserForm")
    public String viewNewUserForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "new_user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserEntity user) {
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
        Page<UserEntity> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);

        userList = page.getContent().stream()
                .map(x -> new User(x.getId(), x.getFirstName(), x.getLastName(), x.getEmail()))
                .collect(Collectors.toList());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("usersList",
                filterBy.isEmpty() ? userList : new FilterUsersHandler().filterUsers(filterBy, userList));
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
        model.addAttribute("filterBy", filterBy);

        return "index";
    }


}
