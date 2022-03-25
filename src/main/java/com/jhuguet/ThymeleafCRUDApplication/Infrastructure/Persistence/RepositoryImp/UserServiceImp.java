package com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.RepositoryImp;

import com.jhuguet.ThymeleafCRUDApplication.Domain.Model.User;
import com.jhuguet.ThymeleafCRUDApplication.Domain.repository.UserService;
import com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.Entity.UserEntity;
import com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        if(!optional.isPresent()) throw new RuntimeException("User not found");
        UserEntity user = optional.get();
        return new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);

        return userRepository.findAll(pageable);
    }

}
