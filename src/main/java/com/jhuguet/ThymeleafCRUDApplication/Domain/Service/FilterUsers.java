package com.jhuguet.ThymeleafCRUDApplication.Domain.Service;

import com.jhuguet.ThymeleafCRUDApplication.Domain.Model.User;

import java.util.List;
import java.util.stream.Collectors;

public class FilterUsers {
    private String filter;

    public FilterUsers(String filter) {
        if(filter.length() > 3) this.filter = filter;
    }
    public List<User> filterUsers(String filter, List<User> initialUsers) {
        return initialUsers.stream()
                .filter(x -> x.getLastName().trim().equalsIgnoreCase(filter) ||
                        x.getFirstName().trim().equalsIgnoreCase(filter))
                .collect(Collectors.toList());
    }
}
