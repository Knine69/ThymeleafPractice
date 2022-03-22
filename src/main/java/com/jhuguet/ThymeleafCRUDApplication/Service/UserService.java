package com.jhuguet.ThymeleafCRUDApplication.Service;

import com.jhuguet.ThymeleafCRUDApplication.Model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(long id);
    void updateUser(User user);
}
