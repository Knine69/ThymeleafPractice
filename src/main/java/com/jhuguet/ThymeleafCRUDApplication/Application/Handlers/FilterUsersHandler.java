package com.jhuguet.ThymeleafCRUDApplication.Application.Handlers;

import com.jhuguet.ThymeleafCRUDApplication.Domain.Model.User;
import com.jhuguet.ThymeleafCRUDApplication.Domain.Service.FilterUsers;

import java.util.List;

public class FilterUsersHandler {
    public List<User> filterUsers(String filter, List<User> initialList){
        return new FilterUsers(this, filter).filterUsers(filter, initialList);
    }
}
