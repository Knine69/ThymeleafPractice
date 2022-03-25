package com.jhuguet.ThymeleafCRUDApplication.Domain.repository;

import com.jhuguet.ThymeleafCRUDApplication.Domain.Model.User;
import com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.Entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    void addUser(UserEntity user);
    User getUserById(long id);
    void deleteUser(long id);
    Page<UserEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
