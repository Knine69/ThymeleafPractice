package com.jhuguet.ThymeleafCRUDApplication.Domain.Service;

import com.jhuguet.ThymeleafCRUDApplication.Domain.Model.User;
import org.springframework.context.ApplicationEvent;

import java.util.List;
import java.util.stream.Collectors;

public class FilterUsers extends ApplicationEvent {
    private String filter;

    public FilterUsers(Object source, String filter) {
        super(source);
        if(filter.length() > 3) {
            this.filter = filter;
        } else {
            throw new IllegalStateException("Requires minimum length to optimally work.");
        }
    }

    public List<User> filterUsers(String filter, List<User> initialUsers) {
        return initialUsers.stream()
                .filter(x -> x.getLastName().trim().equalsIgnoreCase(filter) ||
                        x.getFirstName().trim().equalsIgnoreCase(filter))
                .collect(Collectors.toList());
    }
}
