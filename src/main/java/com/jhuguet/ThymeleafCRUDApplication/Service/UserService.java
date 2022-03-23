package com.jhuguet.ThymeleafCRUDApplication.Service;

import com.jhuguet.ThymeleafCRUDApplication.Model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(long id);
    void deleteUser(long id);
    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
